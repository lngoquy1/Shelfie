package edu.swarthmore.cs.cs71.shelved.model.simple;

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

    //    public String searchForBook() throws IOException {
    //        URL url = new URL("https://www.goodreads.com/search/index.xml/"+this.isbn+"?key="+GOODREADS_KEY);
    //        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    //        connection.setRequestMethod("GET");
    //        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    //        String inputLine;
    //        StringBuffer content = new StringBuffer();
    //        while ((inputLine = in.readLine()) != null) {
    //            content.append(inputLine);
    //        }
    //        in.close();
    //        return content.toString();
    //    }

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

    public String getTitleFromISBN(String ISBN) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
        String workId = this.getGoodreadsId(ISBN);
        URL url = new URL("https://www.goodreads.com/book/show/" + workId);
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
        int begIndexList = getTitleFirst(html);
        int endIndexList = getTitleLast(html);
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
        List<Integer> begIndexList = getIndecesOfTitlesBeg(html);
        List<Integer> endIndexList = getIndecesOfTitlesEnd(html);
        List<String> titleList = new ArrayList<>();
        for (int i=1;i<begIndexList.size();i++){
            String title = html.substring(begIndexList.get(i), endIndexList.get(i));
            titleList.add(title);
        }
        return titleList;
    }

    private int getTitleFirst(String html) {
        int i=1;
        while (i<html.length() && i>=1) {
            int index = html.indexOf("<title>", i);
            if (index != -1){
                return index+7;
            }
            i=index+1;
        }
        return 0;
    }

    private int getTitleLast(String html) {
        int i=1;
        while (i<html.length() && i>=1) {
            int index = html.indexOf("</title>", i);
            if (index != -1){
                return index;
            }
            i=index+1;
        }
        return 0;
    }


    private List getIndecesOfTitlesBeg(String html) {
        List<Integer> indexList= new ArrayList<>();
        int i=1;
        while (i<html.length() && i>=1) {
            int index = html.indexOf("'name'>", i);
            if (index != -1){
                indexList.add(index+7);
            }
            i=index+1;
        }

        return indexList;
    }
    private List getIndecesOfTitlesEnd(String html) {
        List<Integer> indexList = new ArrayList<>();
        int i=1;
        while (i<html.length() && i>=1) {
            int index = html.indexOf("</span></a>      <br/>        <span class=\'by smallText\'>", i);
            if (index != -1){
                indexList.add(index);
            }
            i=index+1;
        }

        return indexList;
    }

}
