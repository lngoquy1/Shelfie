package edu.swarthmore.cs.cs71.shelved.common.junit.tests;

import edu.swarthmore.cs.cs71.shelved.simple.Goodreads;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoodreadsTests {
    @Test
    public void testGetID() throws IOException {
        Goodreads goodreads1 = new Goodreads();
        String id1 = goodreads1.getGoodreadsId("0152047387");
        String realId1 = "116563";
        Assert.assertEquals(id1, realId1);

        Goodreads goodreads2 = new Goodreads();
        String id2 = goodreads2.getGoodreadsId("0439554934");
        String realId2 = "3";
        Assert.assertEquals(id2, realId2);

    }
    @Test
    public void testGetWorkId() throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
        Goodreads goodreads = new Goodreads();
        String searchBuffer = goodreads.getWorkId("0152047387");
        Assert.assertEquals(searchBuffer, "3464");
    }

    @Test
    public void testScrapeRecs() throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
        Goodreads goodreads = new Goodreads();
        List<String> listOfRecs = goodreads.getRecommendedBooks("0152047387");
        List<String> actualList = new ArrayList<>();
        actualList.add("Deep Secret (Magids, #1)");
        actualList.add("Sandry's Book (Circle of Magic, #1)");
        actualList.add("Dragon's Blood (Pit Dragon Chronicles, #1)");
        actualList.add("Calling on Dragons (Enchanted Forest Chronicles, #3)");
        actualList.add("The Secret Country (The Secret Country, #1)");
        actualList.add("Heir Apparent (Rasmussem Corporation, #2)");
        Assert.assertEquals(listOfRecs, actualList);
    }

}
