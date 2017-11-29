package edu.swarthmore.cs.cs71.shelved.model.simple;

import com.google.api.client.json.Json;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Goodreads {
    private final String GOODREADS_KEY = "VCtvMQ3iSjQaSHPXlhGZQA";


    public Goodreads() {
    }



    public String getGoodreadsId(String isbn) throws IOException {
        URL url = new URL("https://www.goodreads.com/book/isbn_to_id/"+isbn+"?key="+GOODREADS_KEY);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }

    private List getAllPossibleBookURLs(String query) throws IOException {
        URL url = new URL("https://www.goodreads.com/search?q="+query.replace(" ","+"));
        String html = getHTML(url);
        List<Integer> begIndexList = getIndexBegList(html,"<a title=");
        List<Integer> extraEndIndexList = getIndecesEndList(html, "<img alt=\"");
        List<Integer> endIndexList = new ArrayList<>();

        for (Integer index:extraEndIndexList) {
            String substr = html.substring(index+("<img alt=").length());
            substr = substr.substring(1,("saving".length()+1));
            if (!substr.equals("saving")){
                endIndexList.add(index);
            }
        }
        List<String> suggWebLinkList = new ArrayList<>();
        for (int i=0;i<begIndexList.size();i++){
            String longHTMLParse = html.substring(begIndexList.get(i), endIndexList.get(i));
            int index = longHTMLParse.indexOf("href=\"");
            String suggWebLink = "https://www.goodreads.com" + longHTMLParse.substring(index+("href=\"").length(),longHTMLParse.length()-12);
            suggWebLinkList.add(suggWebLink);
        }
        return suggWebLinkList;

    }
    public List getISBNFromQuery(String query) throws IOException {
        List<String> urlList = getAllPossibleBookURLs(query);
        List<String> isbnList = new ArrayList<>();
        for (String url:urlList){
            String isbn = getISBN(url);
            if (isbn.length()>0){
                isbnList.add(isbn);
            }
        }
        return isbnList;
    }

    private String getISBN(String strUrl) throws IOException {
        String html = getHTML(new URL(strUrl));
        String initialSearch = "<span itemprop='isbn'>";
        String finalSearch = "</span>)</span>";
        int begIndex = getIndexBegInt(html,initialSearch);
        int endIndex = getIndexEndInt(html,finalSearch);
        String longHTMLParse;
        if (begIndex != 0 && endIndex != 0) {
            initialSearch = "<span itemprop='isbn'>";
            begIndex = getIndexBegInt(html, initialSearch);
            longHTMLParse = html.substring(begIndex, endIndex);
        } else {
            initialSearch = "itemprop='isbn'>";
            begIndex = getIndexBegInt(html,initialSearch);
            longHTMLParse = html.substring(begIndex, begIndex+13);
        }
        if (longHTMLParse.contains("<")){
            longHTMLParse = longHTMLParse.substring(0,longHTMLParse.indexOf("<"));
        }
        return longHTMLParse;
    }

    private String getHTML(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }

    public String getWorkId(String isbn) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {
        Goodreads goodreads = new Goodreads();
        String id = goodreads.getGoodreadsId(isbn);
        URL url = new URL("https://www.goodreads.com/book/id_to_work_id?key="+GOODREADS_KEY+"&id="+id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        String xml = content.toString();
        Element rootElement = getRootElement(xml);
        return getStringElement("item",rootElement);
    }
    // https://stackoverflow.com/questions/4076910/how-to-retrieve-element-value-of-xml-using-java
    private Element getRootElement(String xml) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(xml)));
        return document.getDocumentElement();
    }
    // https://stackoverflow.com/questions/4076910/how-to-retrieve-element-value-of-xml-using-java
    private String getStringElement(String tagName, Element element) {
        NodeList list = element.getElementsByTagName(tagName);
        if (list != null && list.getLength() > 0) {
            NodeList subList = list.item(0).getChildNodes();

            if (subList != null && subList.getLength() > 0) {
                return subList.item(0).getNodeValue();
            }
        }
        return null;
    }

    public String getTitleFromISBNTwo(String ISBN) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
        String goodreadsId = this.getGoodreadsId(ISBN);
        URL url = new URL("https://www.goodreads.com/book/show/" + goodreadsId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        String html = content.toString();
        int begIndexList = getIndexBegInt(html, "<title>");
        int endIndexList = getIndexEndInt(html,"</title>");
        String title = html.substring(begIndexList, endIndexList);
        return title;

    }

    public List<String> getRecommendedBooks(String ISBN) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {
        String workId = this.getWorkId(ISBN);
        URL url = new URL("https://www.goodreads.com/book/similar/"+workId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        String html = content.toString();
        List<Integer> begIndexList = getIndexBegList(html,"'name'>");
        List<Integer> endIndexList = getIndecesEndList(html, "</span></a>      <br/>        <span class=\'by smallText\'>");
        List<String> titleList = new ArrayList<>();
        for (int i=1;i<begIndexList.size();i++){
            String title = html.substring(begIndexList.get(i), endIndexList.get(i));
            titleList.add(title);
        }
        return titleList;
    }

    public JSONObject getJsonFromISBN(String ISBN) throws IOException, ISBNNotFoundException {
        String key = "9NRKX2S8";
        URL url = new URL("http://isbndb.com/api/v2/json/"+key+"/book/"+ISBN);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        int brace = content.indexOf("[");
        if (brace > 0) {
            content.deleteCharAt(brace);
            brace = content.lastIndexOf("]");
            content.deleteCharAt(brace);
            String html = content.toString();
            JSONObject jObj = new JSONObject(html).getJSONObject("data");
            return jObj;
        } else {
            throw new ISBNNotFoundException("The isbn was not found.");
        }
    }

    public String getTitleFromISBN(String ISBN) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException {
        try {
            JSONObject jObj = getJsonFromISBN(ISBN);
            return jObj.getString("title");
        } catch (ISBNNotFoundException e){
            return getTitleFromISBNTwo(ISBN);
        }

    }

    private int getIndexBegInt(String html, String toSearch) {
//        "<title>"
        int i=1;
        while (i<html.length() && i>=1) {
            int index = html.indexOf(toSearch, i);
            if (index != -1){
                return index+toSearch.length();
            }
            i=index+1;
        }
        return 0;
    }
    private int getIndexEndInt(String html, String toSearch) {
        int i=1;
        while (i<html.length() && i>=1) {
            int index = html.indexOf(toSearch, i);
            if (index != -1){
                return index;
            }
            i=index+1;
        }
        return 0;
    }

    private List getIndexBegList(String html, String toSearch) {
        List<Integer> indexList = new ArrayList<>();
        int i = 1;
        while (i < html.length() && i >= 1) {
            int index = html.indexOf(toSearch, i);
            if (index != -1) {
                indexList.add(index + toSearch.length());
            }
            i = index + 1;
        }
        return indexList;
    }



    private List getIndecesEndList(String html, String toSearch) {
        List<Integer> indexList = new ArrayList<>();
        int i=1;
        while (i<html.length() && i>=1) {
            int index = html.indexOf(toSearch, i);
            if (index != -1){
                indexList.add(index);
            }
            i=index+1;
        }

        return indexList;
    }

}
