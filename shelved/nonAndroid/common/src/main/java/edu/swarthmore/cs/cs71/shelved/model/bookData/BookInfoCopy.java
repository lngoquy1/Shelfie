package edu.swarthmore.cs.cs71.shelved.model.bookData;

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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BookInfoCopy {
    private final String GOODREADS_KEY = "VCtvMQ3iSjQaSHPXlhGZQA";


    public BookInfoCopy() {
    }

    //COMBINED section

    //ISBN-based Methods
    public String getTitleFromISBN(String ISBN) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException, EmptyQueryException, NotFoundException {
        try { //get title from GoogleBooks
            JSONObject jObj = getJsonFromQueryGoogle("","",ISBN);
            return getTitleGoogleJson(jObj);
        } catch (EmptyQueryException | NotFoundException f) {
            try { //Get title from ISBNdb
                return getTitleFromISBNdb(ISBN);
            } catch (NotFoundException g) { //Get title from Goodreads scraper
                return getTitleFromISBNGoodreads(ISBN);
            }
        }
    }


    public String getAuthorFromISBN(String ISBN) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException, EmptyQueryException, NotFoundException {
        try { //get author from GoogleBooks
            JSONObject jObj = getJsonFromQueryGoogle("","",ISBN);
            return getAuthorGoogleJson(jObj);
        } catch (EmptyQueryException | NotFoundException f) {
            return getAuthorFromISBNdb(ISBN);
        }
    }


    public String getPublisherFromISBN(String ISBN) throws IOException, NotFoundException {
        return getPublisherFromISBNdb(ISBN);
    }

    public String getNumPagesFromISBN(String ISBN) throws IOException, NotFoundException {
        return getNumPagesFromISBNdb(ISBN);
    }

    public List<String> getRecommendedBooksFromISBN(String ISBN) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
        return getRecommendedBooksGoodreads(ISBN);
    }

    //Title-based Methods
    public List<String> getISBNsFromTitleAndOrAuthor(String title, String author) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException, EmptyQueryException, NotFoundException {
        List<String> ISBNList = new ArrayList<>();
        try {
            JSONObject jObj = getJsonFromQueryGoogle(title, author, "");
            ISBNList = getISBNGoogleJson(jObj);
        } catch (NotFoundException e) {
            ISBNList = getISBNFromTitleAuthorGoodreads(title, author);
            if (ISBNList.isEmpty()){
                throw new NotFoundException("Not found.");
            }
        }
        return ISBNList;
    }


    public String getGenreFromISBN(String ISBN) throws EmptyQueryException, IOException, NotFoundException {
        JSONObject jObj = getJsonFromQueryGoogle("","",ISBN);
        return jObj.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getJSONArray("categories").getString(0);
    }

    public String getUrlBookCoverFromISBN(String isbn) throws EmptyQueryException, IOException, NotFoundException {
        JSONObject jObj = getJsonFromQueryGoogle("","",isbn);
        return jObj.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("thumbnail");
    }
//    public List<String> getAuthorFromTitle(String title) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException, EmptyQueryException, NotFoundException {
//        List<String> isbnList = getISBNsFromTitleAndOrAuthor(title, "");
//        List<String> author = new ArrayList<>();
//        for (String isbn:isbnList){
//            author.add(getAuthorFromISBN(isbn));
//        }
//        return author;
//    }
//
//
//    public List<String> getPublisherFromTitleAndOrAuthor(String title, String author) throws IOException, NotFoundException, SAXException, EmptyQueryException, ParserConfigurationException, XPathExpressionException {
//        List<String> isbnList = getISBNsFromTitleAndOrAuthor(title, author);
//        List<String> publisher = new ArrayList<>();
//        for (String isbn:isbnList){
//            publisher.add(getPublisherFromISBN(isbn));
//        }
//        return publisher;
//    }
//
//    public List<String> getNumPagesFromTitleAndOrAuthor(String title, String author) throws IOException, NotFoundException, SAXException, EmptyQueryException, ParserConfigurationException, XPathExpressionException {
//        List<String> isbnList = getISBNsFromTitleAndOrAuthor(title, author);
//        List<String> NumPages = new ArrayList<>();
//        for (String isbn:isbnList){
//            NumPages.add(getNumPagesFromISBN(isbn));
//        }
//        return NumPages;
//    }

//    public List<String> getRecommendedBooksFromTitleAndOrAuthor(String title, String author) throws ParserConfigurationException, IOException, XPathExpressionException, NotFoundException, SAXException, EmptyQueryException {
//        int upperBound = 5;
//        List<String> isbnList = getISBNsFromTitleAndOrAuthor(title, author);
//        Set<String> recBooksList = new HashSet();//TODO FIX
//        for (int i=0;i<isbnList.size();i++) {
//            if (i<upperBound){
//                String isbn = isbnList.get(i);
//                recBooksList.addAll(getRecommendedBooksGoodreads(isbn));
//            }
//        }
//    }



    //GOODREADS section
    private String getGoodreadsId(String isbn) throws IOException {
        URL url = new URL("https://www.goodreads.com/book/isbn_to_id/"+isbn+"?key="+GOODREADS_KEY);
        StringBuffer content = getHTMLContent(url);
        return content.toString();
    }

    private String getWorkId(String isbn) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {
        BookInfoCopy bookInfoCopy = new BookInfoCopy();
        String id = bookInfoCopy.getGoodreadsId(isbn);
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

    private List<String> getRecommendedBooksGoodreads(String ISBN) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {
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

    public List<String> getISBNFromTitleAuthorGoodreads(String title, String author) throws IOException {
        List<String> urlList = getAllPossibleBookURLs(title+" "+author);
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


    //ISBNDB section

    public JSONObject getJsonFromIsbnDb(String ISBN) throws IOException, NotFoundException {
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
            throw new NotFoundException("The isbn was not found.");
        }
    }

    public String getTitleFromISBNdb(String ISBN) throws IOException, NotFoundException {
        JSONObject jObj = getJsonFromIsbnDb(ISBN);
        return jObj.getString("title");
    }

    public String getAuthorFromISBNdb(String ISBN) throws IOException, NotFoundException {
        //throws NotFoundException if ISBN not found
        JSONObject jObj = getJsonFromIsbnDb(ISBN);
        JSONObject newJObj = (JSONObject) jObj.getJSONArray("author_data").get(0);
        return newJObj.getString("name");
    }

    private String getPublisherFromISBNdb(String ISBN) throws IOException, NotFoundException {
        //throws NotFoundException if ISBN not found
        JSONObject jObj = getJsonFromIsbnDb(ISBN);
        return jObj.getString("publisher_name");
    }
    private String getNumPagesFromISBNdb(String ISBN) throws IOException, NotFoundException {
        //throws NotFoundException if ISBN not found
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
    public JSONObject getJsonFromQueryGoogle(String title, String author, String ISBN) throws IOException, EmptyQueryException, NotFoundException {
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
            throw new NotFoundException("Not found.");
        }
        return jObj;
    }

    private String getTitleGoogleJson(JSONObject jObj) {
        return jObj.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("title");
    }

    private String getAuthorGoogleJson(JSONObject jObj) {
        return jObj.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getJSONArray("authors").getString(0);
    }

    private List<String> getISBNGoogleJson(JSONObject jObj) {
        List<String> ISBNList= new ArrayList<>();
        JSONArray allContent = jObj.getJSONArray("items");
        for (int i=0;i<allContent.length();i++){
            ISBNList.add(allContent.getJSONObject(i).getJSONObject("volumeInfo").getJSONArray("industryIdentifiers").getJSONObject(0).getString("identifier"));
        }
        return ISBNList;
    }

    //General helpers section


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
