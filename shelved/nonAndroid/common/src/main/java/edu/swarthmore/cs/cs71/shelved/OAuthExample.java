package edu.swarthmore.cs.cs71.shelved;

import com.google.api.client.auth.oauth.*;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OAuthExample {
    /**
     * Author: davecahill
     *
     * Adapted from user Sqeezer's StackOverflow post at
     * http://stackoverflow.com/questions/15194182/examples-for-oauth1-using-google-api-java-oauth
     * to work with Goodreads' oAuth API.
     *
     * Get a key / secret by registering at https://www.goodreads.com/api/keys
     * and replace YOUR_KEY_HERE / YOUR_SECRET_HERE in the code below.
     *
     * **Modified ONLY to make it more modular**
     */

    public static final String BASE_GOODREADS_URL = "https://www.goodreads.com";
    public static final String TOKEN_SERVER_URL = BASE_GOODREADS_URL + "/oauth/request_token";
    public static final String AUTHENTICATE_URL = BASE_GOODREADS_URL + "/oauth/authorize";
    public static final String ACCESS_TOKEN_URL = BASE_GOODREADS_URL + "/oauth/access_token";

    public static final String GOODREADS_KEY = "VCtvMQ3iSjQaSHPXlhGZQA";
    public static final String GOODREADS_SECRET = "dm65Yf81dpmCvQleYPiDP5PzKUDRdjfOTjCmG3ZVk";

    private static String buildAuthenticateURL(OAuthCredentialsResponse temporaryTokenResponse) {
        OAuthAuthorizeTemporaryTokenUrl accessTempToken = new OAuthAuthorizeTemporaryTokenUrl(AUTHENTICATE_URL);
        accessTempToken.temporaryToken = temporaryTokenResponse.token;
        return accessTempToken.build();
    }
    private static void buildOathParam(OAuthHmacSigner signer, OAuthCredentialsResponse accessTokenResponse) {
        OAuthParameters oauthParameters = new OAuthParameters();
        signer.tokenSharedSecret = accessTokenResponse.tokenSecret;
        oauthParameters.signer = signer;
        oauthParameters.consumerKey = GOODREADS_KEY;
        oauthParameters.token = accessTokenResponse.token;
    }

    private static OAuthCredentialsResponse getTempToken(OAuthHmacSigner signer) throws IOException {
        OAuthGetTemporaryToken getTemporaryToken = new OAuthGetTemporaryToken(TOKEN_SERVER_URL);
        signer.clientSharedSecret = GOODREADS_SECRET;
        getTemporaryToken.signer = signer;
        getTemporaryToken.consumerKey = GOODREADS_KEY;
        getTemporaryToken.transport = new NetHttpTransport();
        return getTemporaryToken.execute();
    }

    private static OAuthCredentialsResponse getAccessToken(OAuthHmacSigner signer, OAuthCredentialsResponse temporaryTokenResponse) throws IOException {
        OAuthGetAccessToken getAccessToken = new OAuthGetAccessToken(ACCESS_TOKEN_URL);
        getAccessToken.signer = signer;
        signer.tokenSharedSecret = temporaryTokenResponse.tokenSecret;
        getAccessToken.temporaryToken = temporaryTokenResponse.token;
        getAccessToken.transport = new NetHttpTransport();
        getAccessToken.consumerKey = GOODREADS_KEY;
        return getAccessToken.execute();
    }

    private static void authenticateURL(String authUrl) throws InterruptedException {
        System.out.println("Goodreads oAuth sample: Please visit the following URL to authorize:");
        System.out.println(authUrl);
        System.out.println("Waiting 4s to allow time for visiting auth URL and authorizing...");
        Thread.sleep(4000);
        System.out.println("Waiting time complete - assuming access granted and attempting to get access token");
    }

    private static StringBuffer saveResponse(HttpURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content;
    }

    private static HttpURLConnection getIdFromISBM(String isbn) throws IOException {
        URL url = new URL("https://www.goodreads.com/book/isbn_to_id/"+isbn+"?key="+GOODREADS_KEY);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }


    // GOES IN MAIN
    // OAuthHmacSigner signer = new OAuthHmacSigner();
    // Get Temporary Token
    // OAuthCredentialsResponse temporaryTokenResponse = getTempToken(signer);
    // Build Authenticate URL
    // String authUrl = buildAuthenticateURL(temporaryTokenResponse);
    // Redirect to Authenticate URL in order to get Verifier Code
    // authenticateURL(authUrl);
    // Get Access Token using Temporary token and Verifier Code
    // OAuthCredentialsResponse accessTokenResponse = getAccessToken(signer, temporaryTokenResponse);
    // buildOathParam(signer, accessTokenResponse);
    //END AUTHOR: DAVECAHIL. The next section was ours!

}
