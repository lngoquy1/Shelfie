package edu.swarthmore.cs.cs71.shelved.model.simple;

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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BookInfo {
    private final String GOODREADS_KEY = "VCtvMQ3iSjQaSHPXlhGZQA";


    public BookInfo() {
    }

    //COMBINED section
    public String getTitleFromISBN(String ISBN) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException, EmptyQueryException, notFoundException {
        try { //get title from GoogleBooks
            JSONObject jObj = getJsonFromQueryGoogle("","",ISBN);
            return getTitleGoogleJson(jObj);
        } catch (EmptyQueryException | notFoundException f) {
            try { //Get title from ISBNdb
                return getTitleFromISBNdb(ISBN);
            } catch (notFoundException g) { //Get title from Goodreads scraper
                return getTitleFromISBNGoodreads(ISBN);
            }
        }
    }


    public String getAuthorFromISBN(String ISBN) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException, EmptyQueryException, notFoundException {
        try { //get author from GoogleBooks
            JSONObject jObj = getJsonFromQueryGoogle("","",ISBN);
            return getAuthorGoogleJson(jObj);
        } catch (EmptyQueryException | notFoundException f) {
            return getAuthorFromISBNdb(ISBN);
        }
    }

    //GOODREADS section
    public String getGoodreadsId(String isbn) throws IOException {
        URL url = new URL("https://www.goodreads.com/book/isbn_to_id/"+isbn+"?key="+GOODREADS_KEY);
        StringBuffer content = getHTMLContent(url);
        return content.toString();
    }

    public String getWorkId(String isbn) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {
        BookInfo bookInfo = new BookInfo();
        String id = bookInfo.getGoodreadsId(isbn);
        URL url = new URL("https://www.goodreads.com/book/id_to_work_id?key="+GOODREADS_KEY+"&id="+id);
        StringBuffer content = getHTMLContent(url);

        String xml = content.toString();
        Element rootElement = getRootElement(xml);
        return getStringElement("item",rootElement);
    }



    private List getAllPossibleBookURLs(String query) throws IOException {
        URL url = new URL("https://www.goodreads.com/search?q="+query.replace(" ","+"));
        String html = getHTMLContent(url).toString();
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



    public String getTitleFromISBNGoodreads(String ISBN) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
        String goodreadsId = this.getGoodreadsId(ISBN);
        URL url = new URL("https://www.goodreads.com/book/show/" + goodreadsId);
        StringBuffer content = getHTMLContent(url);
        String html = content.toString();
        int begIndexList = getIndexBegInt(html, "<title>");
        int endIndexList = getIndexEndInt(html,"</title>");
        String title = html.substring(begIndexList, endIndexList);
        return title;

    }

    public List<String> getRecommendedBooks(String ISBN) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {
        String workId = this.getWorkId(ISBN);
        URL url = new URL("https://www.goodreads.com/book/similar/"+workId);
        StringBuffer content = getHTMLContent(url);
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



    //ISBNDB section

    public JSONObject getJsonFromIsbnDb(String ISBN) throws IOException, notFoundException {
        String key = "9NRKX2S8";
        URL url = new URL("http://isbndb.com/api/v2/json/"+key+"/book/"+ISBN);
        StringBuffer content = getHTMLContent(url);
        int brace = content.indexOf("[");
        if (brace > 0) {
            content.deleteCharAt(brace);
            brace = content.lastIndexOf("]");
            content.deleteCharAt(brace);
            String html = content.toString();
            JSONObject jObj = new JSONObject(html).getJSONObject("data");
            return jObj;
        } else {
            throw new notFoundException("The isbn was not found.");
        }
    }

    public String getTitleFromISBNdb(String ISBN) throws IOException, notFoundException {
        JSONObject jObj = getJsonFromIsbnDb(ISBN);
        return jObj.getString("title");
    }

    public String getAuthorFromISBNdb(String ISBN) throws IOException, notFoundException {
        //throws notFoundException if ISBN not found
        JSONObject jObj = getJsonFromIsbnDb(ISBN);
        JSONObject newJObj = (JSONObject) jObj.getJSONArray("author_data").get(0);
        return newJObj.getString("name");
    }

    public String getPublisherFromISBNdb(String ISBN) throws IOException, notFoundException {
        //throws notFoundException if ISBN not found
        JSONObject jObj = getJsonFromIsbnDb(ISBN);
        return jObj.getString("publisher_name");
    }
    public String getNumPagesFromISBNdb(String ISBN) throws IOException, notFoundException {
        //throws notFoundException if ISBN not found
        JSONObject jObj = getJsonFromIsbnDb(ISBN);
        String physDesc = jObj.getString("physical_description_text");
        int indexOfPages = physDesc.indexOf("pages");
        StringBuilder pagesBuilder = new StringBuilder();
        for (int i=indexOfPages-2;i>=0;i--){
            char c = physDesc.charAt(i);
            boolean cIsDigit = (c >= '0' && c <= '9');
            if (cIsDigit){
                pagesBuilder.append(c);
            } else {
                break;
            }
        }
        return pagesBuilder.reverse().toString();
    }

//    public String getCoverUrlFromISBN(String ISBN){
//        return "covers.openlibrary.org/b/isbn/"+ISBN+"-M.jpg";
//    }

//    https://www.googleapis.com/books/v1/volumes?q=intitle:so%20you%20want%20to%20be%20a%20wizard
    //GOOGLEAPIS Section
    public JSONObject getJsonFromQueryGoogle(String title, String author, String ISBN) throws IOException, EmptyQueryException, notFoundException {
        //takes a title, author, title and author, or isbn
        if (title.isEmpty() && author.isEmpty() && ISBN.isEmpty()){
            throw new EmptyQueryException("No search terms entered");
        }
        title = title.replace(" ","+");
        author = author.replace(" ","+");
        ISBN = ISBN.replace("-","");
        StringBuilder urlStringBuffer = new StringBuilder();
        if (!ISBN.isEmpty()){
            urlStringBuffer.append("https://www.googleapis.com/books/v1/volumes?q=isbn:"+ISBN);
        } else {
            urlStringBuffer.append("https://www.googleapis.com/books/v1/volumes?q=");
            if (!title.isEmpty()) {
                urlStringBuffer.append("+intitle:" + title);
            }
            if (!author.isEmpty()) {
                urlStringBuffer.append("+inauthor:" + author);
            }
        }
        URL url = new URL(urlStringBuffer.toString());
        StringBuffer content = getHTMLContent(url);
        JSONObject jObj = new JSONObject(content.toString());
        String numFound = jObj.get("totalItems").toString();
        if (Integer.parseInt(numFound) == 0){
            throw new notFoundException("Not found.");
        }
        return jObj;
    }

    private String getTitleGoogleJson(JSONObject jObj) {
        return jObj.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("title");
    }

    private String getAuthorGoogleJson(JSONObject jObj) {
        return jObj.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getJSONArray("authors").getString(0);
    }


    //HELPERS section

    private String getISBN(String strUrl) throws IOException {
        String html = getHTMLContent(new URL(strUrl)).toString();
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


    private StringBuffer getHTMLContent(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content;
    }


    private int getIndexBegInt(String html, String toSearch) {
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
