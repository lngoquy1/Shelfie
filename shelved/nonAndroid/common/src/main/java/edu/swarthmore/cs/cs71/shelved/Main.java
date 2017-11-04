package edu.swarthmore.cs.cs71.shelved;

        import com.google.api.client.auth.oauth.OAuthAuthorizeTemporaryTokenUrl;
        import com.google.api.client.auth.oauth.OAuthCredentialsResponse;
        import com.google.api.client.auth.oauth.OAuthGetAccessToken;
        import com.google.api.client.auth.oauth.OAuthGetTemporaryToken;
        import com.google.api.client.auth.oauth.OAuthHmacSigner;
        import com.google.api.client.auth.oauth.OAuthParameters;
        import com.google.api.client.http.GenericUrl;
        import com.google.api.client.http.HttpRequestFactory;
        import com.google.api.client.http.HttpResponse;
        import com.google.api.client.http.apache.ApacheHttpTransport;
        import com.google.api.client.http.javanet.NetHttpTransport;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;

public class Main {
    /**
     * Author: davecahill
     *
     * Adapted from user Sqeezer's StackOverflow post at
     * http://stackoverflow.com/questions/15194182/examples-for-oauth1-using-google-api-java-oauth
     * to work with Goodreads' oAuth API.
     *
     * Get a key / secret by registering at https://www.goodreads.com/api/keys
     * and replace YOUR_KEY_HERE / YOUR_SECRET_HERE in the code below.
     */

    public static final String BASE_GOODREADS_URL = "https://www.goodreads.com";
    public static final String TOKEN_SERVER_URL = BASE_GOODREADS_URL + "/oauth/request_token";
    public static final String AUTHENTICATE_URL = BASE_GOODREADS_URL + "/oauth/authorize";
    public static final String ACCESS_TOKEN_URL = BASE_GOODREADS_URL + "/oauth/access_token";

    public static final String GOODREADS_KEY = "VCtvMQ3iSjQaSHPXlhGZQA";
    public static final String GOODREADS_SECRET = "dm65Yf81dpmCvQleYPiDP5PzKUDRdjfOTjCmG3ZVk";

    public static void main(String[] args) throws IOException, InterruptedException {
        OAuthHmacSigner signer = new OAuthHmacSigner();
        // Get Temporary Token
        OAuthGetTemporaryToken getTemporaryToken = new OAuthGetTemporaryToken(TOKEN_SERVER_URL);
        signer.clientSharedSecret = GOODREADS_SECRET;
        getTemporaryToken.signer = signer;
        getTemporaryToken.consumerKey = GOODREADS_KEY;
        getTemporaryToken.transport = new NetHttpTransport();
        OAuthCredentialsResponse temporaryTokenResponse = getTemporaryToken.execute();

        // Build Authenticate URL
        OAuthAuthorizeTemporaryTokenUrl accessTempToken = new OAuthAuthorizeTemporaryTokenUrl(AUTHENTICATE_URL);
        accessTempToken.temporaryToken = temporaryTokenResponse.token;
        String authUrl = accessTempToken.build();

        // Redirect to Authenticate URL in order to get Verifier Code
        System.out.println("Goodreads oAuth sample: Please visit the following URL to authorize:");
        System.out.println(authUrl);
        System.out.println("Waiting 4s to allow time for visiting auth URL and authorizing...");
        Thread.sleep(4000);

        System.out.println("Waiting time complete - assuming access granted and attempting to get access token");
        // Get Access Token using Temporary token and Verifier Code
        OAuthGetAccessToken getAccessToken = new OAuthGetAccessToken(ACCESS_TOKEN_URL);
        getAccessToken.signer = signer;
        // NOTE: This is the main difference from the StackOverflow example
        signer.tokenSharedSecret = temporaryTokenResponse.tokenSecret;
        getAccessToken.temporaryToken = temporaryTokenResponse.token;
        getAccessToken.transport = new NetHttpTransport();
        getAccessToken.consumerKey = GOODREADS_KEY;
        OAuthCredentialsResponse accessTokenResponse = getAccessToken.execute();

        // Build OAuthParameters in order to use them while accessing the resource
        OAuthParameters oauthParameters = new OAuthParameters();
        signer.tokenSharedSecret = accessTokenResponse.tokenSecret;
        oauthParameters.signer = signer;
        oauthParameters.consumerKey = GOODREADS_KEY;
        oauthParameters.token = accessTokenResponse.token;

        /**
         * END Author: davecahill. The next section was ours!
        **/

        // send HTTP GET request for GoodReads ID given ISBN
        String isbn = "0152047387";
        URL url = new URL("https://www.goodreads.com/book/isbn_to_id/"+isbn+"?key="+GOODREADS_KEY);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // save response in StringBuffer "content"
        // Credit to http://www.baeldung.com/java-http-request
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        // set bookId from content and print
        String bookId = content.toString();
        System.out.println(bookId);
    }
}