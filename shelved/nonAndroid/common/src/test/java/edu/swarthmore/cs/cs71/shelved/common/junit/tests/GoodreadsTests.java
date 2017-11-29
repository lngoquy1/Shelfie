//package edu.swarthmore.cs.cs71.shelved.common.junit.tests;
//
//import edu.swarthmore.cs.cs71.shelved.model.simple.Goodreads;
//import edu.swarthmore.cs.cs71.shelved.model.simple.ISBNNotFoundException;
//import org.json.JSONObject;
//import org.junit.Assert;
//import org.junit.Test;
//import org.xml.sax.SAXException;
//
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.xpath.XPathExpressionException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class GoodreadsTests {
//    @Test
//    public void testGetID() throws IOException {
//        Goodreads goodreads1 = new Goodreads();
//        String id1 = goodreads1.getGoodreadsId("0152047387");
//        String realId1 = "116563";
//        Assert.assertEquals(id1, realId1);
//
//        Goodreads goodreads2 = new Goodreads();
//        String id2 = goodreads2.getGoodreadsId("0439554934");
//        String realId2 = "3";
//        Assert.assertEquals(id2, realId2);
//
//    }
//    @Test
//    public void testGetWorkId() throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
//        Goodreads goodreads = new Goodreads();
//        String searchBuffer = goodreads.getWorkId("0152047387");
//        Assert.assertEquals(searchBuffer, "3464");
//    }
//
//    @Test
//    public void testScrapeRecs() throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
//        Goodreads goodreads = new Goodreads();
//        List<String> listOfRecs = goodreads.getRecommendedBooks("0152047387");
//        List<String> actualList = new ArrayList<>();
//        actualList.add("Deep Secret (Magids, #1)");
//        actualList.add("Sandry's Book (Circle of Magic, #1)");
//        actualList.add("Dragon's Blood (Pit Dragon Chronicles, #1)");
//        actualList.add("Calling on Dragons (Enchanted Forest Chronicles, #3)");
//        actualList.add("The Secret Country (The Secret Country, #1)");
//        actualList.add("Heir Apparent (Rasmussem Corporation, #2)");
//        Assert.assertEquals(listOfRecs, actualList);
//    }
//    @Test
//    public void testScrapeLink() throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
//        //this tests that "Harry Potter and the Sorcerer's Stone" returns all **possible** book isbns (some goodreads sources don't list the isbns easily)
//        Goodreads goodreads = new Goodreads();
//        List<String> listOfISBNs = goodreads.getISBNFromQuery("Harry Potter and the Sorcerer's Stone");
//        List<String> customListOfISBNs = new ArrayList<>();
//        customListOfISBNs.add("9780439554930");
//        customListOfISBNs.add("2940000829790");
//        customListOfISBNs.add("B005E0QXGG");
//        customListOfISBNs.add("9780439211161");
//        customListOfISBNs.add("9781548602642");
//        customListOfISBNs.add("9780439294829");
//        customListOfISBNs.add("9785550118191");
//        customListOfISBNs.add("2940000052372");
//        customListOfISBNs.add("9780757991714");
//        customListOfISBNs.add("9780887246586");
//        customListOfISBNs.add("2940012085788");
//        customListOfISBNs.add("9781602491793");
//        customListOfISBNs.add("9781934840573");
//        Assert.assertEquals(listOfISBNs, customListOfISBNs);
//    }
//    @Test
//    public void testJsonISBN() throws IOException, ISBNNotFoundException, ParserConfigurationException, XPathExpressionException, SAXException {
//        Goodreads goodreads = new Goodreads();
//        String title = goodreads.getTitleFromISBN("0552547344");
//        String title2 = goodreads.getTitleFromISBN("1439171882");
//        System.out.println(title);
//        System.out.println(title2);
//        Assert.assertEquals(title, "(Un)arranged Marriage");
//        Assert.assertEquals(title2, "Breakfast with Socrates: An Extraordinary (Philosophical) Journey Through Your Ordinary Day by Robert Rowland Smith");
//    }
//}
