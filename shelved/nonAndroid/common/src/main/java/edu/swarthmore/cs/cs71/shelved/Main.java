package edu.swarthmore.cs.cs71.shelved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static final String GOODREADS_KEY = "VCtvMQ3iSjQaSHPXlhGZQA";

    private static HttpURLConnection getIdFromISBM(String isbn) throws IOException {
        URL url = new URL("https://www.goodreads.com/book/isbn_to_id/"+isbn+"?key="+GOODREADS_KEY);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
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


    public static void main(String[] args) throws IOException {
        String isbn = "0152047387";
        HttpURLConnection connection = getIdFromISBM(isbn);

        // save response in StringBuffer "content"
        // Credit to http://www.baeldung.com/java-http-request
        StringBuffer content = saveResponse(connection);

        // set bookId from content and print
        String bookId = content.toString();
        System.out.println(bookId);
    }



}