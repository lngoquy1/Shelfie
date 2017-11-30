package edu.swarthmore.cs.cs71.shelved.common.junit.tests;

import edu.swarthmore.cs.cs71.shelved.model.simple.BookInfo;
import edu.swarthmore.cs.cs71.shelved.model.simple.EmptyQueryException;
import edu.swarthmore.cs.cs71.shelved.model.simple.notFoundException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookInfoTests {
    @Test
    public void testGetID() throws IOException {
        BookInfo bookInfo1 = new BookInfo();
        String id1 = bookInfo1.getGoodreadsId("0152047387");
        String realId1 = "116563";
        Assert.assertEquals(realId1, id1);

        BookInfo bookInfo2 = new BookInfo();
        String id2 = bookInfo2.getGoodreadsId("0439554934");
        String realId2 = "3";
        Assert.assertEquals(realId2, id2);

    }
    @Test
    public void testGetWorkId() throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
        BookInfo bookInfo = new BookInfo();
        String searchBuffer = bookInfo.getWorkId("0152047387");
        Assert.assertEquals("3464", searchBuffer);
    }

    @Test
    public void testNonExceptionGetters() throws IOException, ParserConfigurationException, XPathExpressionException, SAXException, notFoundException, EmptyQueryException {
        BookInfo bookInfo = new BookInfo();
        String title = bookInfo.getTitleFromISBN("0552547344");
        String title2 = bookInfo.getTitleFromISBN("1439171882");
        Assert.assertEquals("(Un)arranged Marriage", title);
        Assert.assertEquals("Breakfast with Socrates", title2);
        String author = bookInfo.getAuthorFromISBNdb("0552547344");
        Assert.assertEquals("Bali Rai", author);
        String publisher = bookInfo.getPublisherFromISBNdb("0552547344");
        Assert.assertEquals("Corgi Childrens", publisher);
        String pages = bookInfo.getNumPagesFromISBNdb("0552547344");
        Assert.assertEquals("272", pages);
        String pages2 = bookInfo.getNumPagesFromISBNdb("0439554934");
        Assert.assertEquals("320", pages2);

        String author2 = bookInfo.getAuthorFromISBN("0552547344");
        Assert.assertEquals("Bali Rai", author2);
        String author3 = bookInfo.getAuthorFromISBN("1439171882");
        Assert.assertEquals("Robert Rowland Smith", author3);
        String author4 = bookInfo.getAuthorFromISBNdb("0552547344");
        Assert.assertEquals("Bali Rai", author4);


    }

    @Test(expected = notFoundException.class)
    public void getTitleFromISBNTest() throws IOException, EmptyQueryException, notFoundException {
        BookInfo bookInfo = new BookInfo();
        JSONObject jObj = bookInfo.getJsonFromQueryGoogle("","","05450s10225");
        String title = jObj.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("title");
    }

    @Test(expected = notFoundException.class)
    public void testExceptionAuthor() throws IOException, notFoundException {
        BookInfo bookInfo = new BookInfo();
        String author2 = bookInfo.getAuthorFromISBNdb("1439171882");
    }
    @Test(expected = notFoundException.class)
    public void testExceptionPublisher() throws IOException, notFoundException {
        BookInfo bookInfo = new BookInfo();
        String publisher2 = bookInfo.getPublisherFromISBNdb("1439171882");
    }
    @Test(expected = notFoundException.class)
    public void testExceptionPages() throws IOException, notFoundException {
        BookInfo bookInfo = new BookInfo();
        String pages3 = bookInfo.getNumPagesFromISBNdb("1439171882");
    }
    @Test
    public void testJsonFromQueryGoogleBooks() throws IOException, EmptyQueryException, notFoundException {
        BookInfo bookInfo = new BookInfo();
        JSONObject json2 = bookInfo.getJsonFromQueryGoogle("So you want to be a wizard","Diane Duane","");
        Assert.assertEquals("{\"totalItems\":2,\"kind\":\"books#volumes\",\"items\":[{\"saleInfo\":{\"offers\":[{\"finskyOfferType\":1,\"retailPrice\":{\"amountInMicros\":3990000,\"currencyCode\":\"USD\"},\"listPrice\":{\"amountInMicros\":3990000,\"currencyCode\":\"USD\"},\"giftable\":true}],\"country\":\"US\",\"isEbook\":true,\"saleability\":\"FOR_SALE\",\"buyLink\":\"https://play.google.com/store/books/details?id=dfXYy0rj0S0C&rdid=book-dfXYy0rj0S0C&rdot=1&source=gbs_api\",\"retailPrice\":{\"amount\":3.99,\"currencyCode\":\"USD\"},\"listPrice\":{\"amount\":3.99,\"currencyCode\":\"USD\"}},\"searchInfo\":{\"textSnippet\":\"But this one said, So You Want to Be a Wizard. I don&#39;t belive this, Nina thought. She shut the book and stood there holding it in her hand, confused, amazed, suspicious\\u2014and delighted. If it was a joke, it was a great one. If it wasn&#39;t .\"},\"kind\":\"books#volume\",\"volumeInfo\":{\"industryIdentifiers\":[{\"identifier\":\"0547545118\",\"type\":\"ISBN_10\"},{\"identifier\":\"9780547545110\",\"type\":\"ISBN_13\"}],\"pageCount\":400,\"printType\":\"BOOK\",\"readingModes\":{\"image\":true,\"text\":true},\"previewLink\":\"http://books.google.com/books?id=dfXYy0rj0S0C&printsec=frontcover&dq=intitle:So+you+want+to+be+a+wizard+inauthor:Diane+Duane&hl=&cd=1&source=gbs_api\",\"canonicalVolumeLink\":\"https://market.android.com/details?id=book-dfXYy0rj0S0C\",\"description\":\"Something stopped Nita's hand as it ran along the bookshelf. She looked and found that one of the books had a loose thread at the top of its spine. It was one of those So You Want to Be a . . . books, a series on careers. So You Want to Be a Pilot, and a Scientist . . . a Writer. But this one said, So You Want to Be a Wizard. I don't belive this, Nina thought. She shut the book and stood there holding it in her hand, confused, amazed, suspicious\\u2014and delighted. If it was a joke, it was a great one. If it wasn't . . . ?\",\"language\":\"en\",\"title\":\"So You Want to Be a Wizard\",\"imageLinks\":{\"thumbnail\":\"http://books.google.com/books/content?id=dfXYy0rj0S0C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\",\"smallThumbnail\":\"http://books.google.com/books/content?id=dfXYy0rj0S0C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\"},\"averageRating\":4.5,\"panelizationSummary\":{\"containsImageBubbles\":false,\"containsEpubBubbles\":false},\"publisher\":\"Houghton Mifflin Harcourt\",\"ratingsCount\":11,\"publishedDate\":\"2003-10-01\",\"categories\":[\"Juvenile Fiction\"],\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":true,\"contentVersion\":\"0.11.8.0.preview.3\",\"authors\":[\"Diane Duane\"],\"infoLink\":\"https://play.google.com/store/books/details?id=dfXYy0rj0S0C&source=gbs_api\"},\"etag\":\"2M3V8aQIXP8\",\"id\":\"dfXYy0rj0S0C\",\"accessInfo\":{\"accessViewStatus\":\"SAMPLE\",\"country\":\"US\",\"viewability\":\"PARTIAL\",\"pdf\":{\"isAvailable\":true,\"acsTokenLink\":\"http://books.google.com/books/download/So_You_Want_to_Be_a_Wizard-sample-pdf.acsm?id=dfXYy0rj0S0C&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"},\"webReaderLink\":\"http://play.google.com/books/reader?id=dfXYy0rj0S0C&hl=&printsec=frontcover&source=gbs_api\",\"epub\":{\"isAvailable\":true,\"acsTokenLink\":\"http://books.google.com/books/download/So_You_Want_to_Be_a_Wizard-sample-epub.acsm?id=dfXYy0rj0S0C&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"},\"publicDomain\":false,\"quoteSharingAllowed\":false,\"embeddable\":true,\"textToSpeechPermission\":\"ALLOWED\"},\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/dfXYy0rj0S0C\"},{\"saleInfo\":{\"country\":\"US\",\"isEbook\":false,\"saleability\":\"NOT_FOR_SALE\"},\"searchInfo\":{\"textSnippet\":\"During a summer vacation at the beach, thirteen-year-old wizard Nita and her friend Kit assist the whale-wizard S&#39;reee in combating an evil power.\"},\"kind\":\"books#volume\",\"volumeInfo\":{\"industryIdentifiers\":[{\"identifier\":\"9780152162504\",\"type\":\"ISBN_13\"},{\"identifier\":\"015216250X\",\"type\":\"ISBN_10\"}],\"pageCount\":371,\"printType\":\"BOOK\",\"readingModes\":{\"image\":false,\"text\":true},\"previewLink\":\"http://books.google.com/books?id=DHE1ngEACAAJ&dq=intitle:So+you+want+to+be+a+wizard+inauthor:Diane+Duane&hl=&cd=2&source=gbs_api\",\"canonicalVolumeLink\":\"https://books.google.com/books/about/Deep_Wizardry.html?hl=&id=DHE1ngEACAAJ\",\"description\":\"During a summer vacation at the beach, thirteen-year-old wizard Nita and her friend Kit assist the whale-wizard S'reee in combating an evil power.\",\"language\":\"en\",\"title\":\"Deep Wizardry\",\"imageLinks\":{\"thumbnail\":\"http://books.google.com/books/content?id=DHE1ngEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api\",\"smallThumbnail\":\"http://books.google.com/books/content?id=DHE1ngEACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api\"},\"averageRating\":3,\"publisher\":\"Houghton Mifflin Harcourt\",\"ratingsCount\":2,\"publishedDate\":\"1985\",\"categories\":[\"Fantasy\"],\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":true,\"contentVersion\":\"0.6.5.0.preview.2\",\"infoLink\":\"http://books.google.com/books?id=DHE1ngEACAAJ&dq=intitle:So+you+want+to+be+a+wizard+inauthor:Diane+Duane&hl=&source=gbs_api\"},\"etag\":\"xYWB7DDwchY\",\"id\":\"DHE1ngEACAAJ\",\"accessInfo\":{\"accessViewStatus\":\"NONE\",\"country\":\"US\",\"viewability\":\"NO_PAGES\",\"pdf\":{\"isAvailable\":true},\"webReaderLink\":\"http://play.google.com/books/reader?id=DHE1ngEACAAJ&hl=&printsec=frontcover&source=gbs_api\",\"epub\":{\"isAvailable\":true},\"publicDomain\":false,\"quoteSharingAllowed\":false,\"embeddable\":false,\"textToSpeechPermission\":\"ALLOWED\"},\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/DHE1ngEACAAJ\"}]}",json2.toString());
        JSONObject json3 = bookInfo.getJsonFromQueryGoogle("","","9780152162504");
        Assert.assertEquals("{\"totalItems\":3,\"kind\":\"books#volumes\",\"items\":[{\"saleInfo\":{\"country\":\"US\",\"isEbook\":false,\"saleability\":\"NOT_FOR_SALE\"},\"searchInfo\":{\"textSnippet\":\"During a summer vacation at the beach, thirteen-year-old wizard Nita and her friend Kit assist the whale-wizard S&#39;reee in combating an evil power.\"},\"kind\":\"books#volume\",\"volumeInfo\":{\"industryIdentifiers\":[{\"identifier\":\"9780152162504\",\"type\":\"ISBN_13\"},{\"identifier\":\"015216250X\",\"type\":\"ISBN_10\"}],\"pageCount\":371,\"printType\":\"BOOK\",\"readingModes\":{\"image\":false,\"text\":true},\"previewLink\":\"http://books.google.com/books?id=DHE1ngEACAAJ&dq=isbn:9780152162504&hl=&cd=1&source=gbs_api\",\"canonicalVolumeLink\":\"https://books.google.com/books/about/Deep_Wizardry.html?hl=&id=DHE1ngEACAAJ\",\"description\":\"During a summer vacation at the beach, thirteen-year-old wizard Nita and her friend Kit assist the whale-wizard S'reee in combating an evil power.\",\"language\":\"en\",\"title\":\"Deep Wizardry\",\"imageLinks\":{\"thumbnail\":\"http://books.google.com/books/content?id=DHE1ngEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api\",\"smallThumbnail\":\"http://books.google.com/books/content?id=DHE1ngEACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api\"},\"averageRating\":3,\"publisher\":\"Houghton Mifflin Harcourt\",\"ratingsCount\":2,\"publishedDate\":\"1985\",\"categories\":[\"Fantasy\"],\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":true,\"contentVersion\":\"0.6.5.0.preview.2\",\"infoLink\":\"http://books.google.com/books?id=DHE1ngEACAAJ&dq=isbn:9780152162504&hl=&source=gbs_api\"},\"etag\":\"3W7NjSOpN0s\",\"id\":\"DHE1ngEACAAJ\",\"accessInfo\":{\"accessViewStatus\":\"NONE\",\"country\":\"US\",\"viewability\":\"NO_PAGES\",\"pdf\":{\"isAvailable\":true},\"webReaderLink\":\"http://play.google.com/books/reader?id=DHE1ngEACAAJ&hl=&printsec=frontcover&source=gbs_api\",\"epub\":{\"isAvailable\":true},\"publicDomain\":false,\"quoteSharingAllowed\":false,\"embeddable\":false,\"textToSpeechPermission\":\"ALLOWED\"},\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/DHE1ngEACAAJ\"},{\"saleInfo\":{\"country\":\"US\",\"isEbook\":false,\"saleability\":\"NOT_FOR_SALE\"},\"searchInfo\":{\"textSnippet\":\"During a summer vacation at the beach, thirteen-year-old wizard Nita and her friend Kit assist the whale-wizard S&#39;reee in combating an evil power.\"},\"kind\":\"books#volume\",\"volumeInfo\":{\"industryIdentifiers\":[{\"identifier\":\"9780152162504\",\"type\":\"ISBN_13\"},{\"identifier\":\"015216250X\",\"type\":\"ISBN_10\"}],\"pageCount\":371,\"printType\":\"BOOK\",\"readingModes\":{\"image\":true,\"text\":true},\"previewLink\":\"http://books.google.com/books?id=cEI3wr0fvs0C&printsec=frontcover&dq=isbn:9780152162504&hl=&cd=2&source=gbs_api\",\"canonicalVolumeLink\":\"https://books.google.com/books/about/Deep_Wizardry.html?hl=&id=cEI3wr0fvs0C\",\"description\":\"During a summer vacation at the beach, thirteen-year-old wizard Nita and her friend Kit assist the whale-wizard S'reee in combating an evil power.\",\"language\":\"en\",\"title\":\"Deep Wizardry\",\"imageLinks\":{\"thumbnail\":\"http://books.google.com/books/content?id=cEI3wr0fvs0C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\",\"smallThumbnail\":\"http://books.google.com/books/content?id=cEI3wr0fvs0C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\"},\"averageRating\":3,\"publisher\":\"Houghton Mifflin Harcourt\",\"ratingsCount\":2,\"publishedDate\":\"1985\",\"categories\":[\"Fantasy\"],\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":true,\"contentVersion\":\"0.6.5.0.preview.3\",\"infoLink\":\"http://books.google.com/books?id=cEI3wr0fvs0C&dq=isbn:9780152162504&hl=&source=gbs_api\"},\"etag\":\"xsB8OCyL68U\",\"id\":\"cEI3wr0fvs0C\",\"accessInfo\":{\"accessViewStatus\":\"SAMPLE\",\"country\":\"US\",\"viewability\":\"PARTIAL\",\"pdf\":{\"isAvailable\":true,\"acsTokenLink\":\"http://books.google.com/books/download/Deep_Wizardry-sample-pdf.acsm?id=cEI3wr0fvs0C&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"},\"webReaderLink\":\"http://play.google.com/books/reader?id=cEI3wr0fvs0C&hl=&printsec=frontcover&source=gbs_api\",\"epub\":{\"isAvailable\":true,\"acsTokenLink\":\"http://books.google.com/books/download/Deep_Wizardry-sample-epub.acsm?id=cEI3wr0fvs0C&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"},\"publicDomain\":false,\"quoteSharingAllowed\":false,\"embeddable\":true,\"textToSpeechPermission\":\"ALLOWED\"},\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/cEI3wr0fvs0C\"},{\"saleInfo\":{\"country\":\"US\",\"isEbook\":false,\"saleability\":\"NOT_FOR_SALE\"},\"kind\":\"books#volume\",\"volumeInfo\":{\"industryIdentifiers\":[{\"identifier\":\"015216250X\",\"type\":\"ISBN_10\"},{\"identifier\":\"9780152162504\",\"type\":\"ISBN_13\"}],\"pageCount\":385,\"printType\":\"BOOK\",\"readingModes\":{\"image\":false,\"text\":false},\"previewLink\":\"http://books.google.com/books?id=sbgn_U3h77AC&pg=PP1&dq=isbn:9780152162504&hl=&cd=3&source=gbs_api\",\"canonicalVolumeLink\":\"https://books.google.com/books/about/So_You_Want_to_be_a_Wizard.html?hl=&id=sbgn_U3h77AC\",\"description\":\"Thirteen-year-old Nita, tormented by a gang of bullies because she won't fight back, finds the help she needs in a library book on wizardry which guides her into another dimension.\",\"language\":\"en\",\"title\":\"So You Want to be a Wizard\",\"imageLinks\":{\"thumbnail\":\"http://books.google.com/books/content?id=sbgn_U3h77AC&printsec=frontcover&img=1&zoom=1&source=gbs_api\",\"smallThumbnail\":\"http://books.google.com/books/content?id=sbgn_U3h77AC&printsec=frontcover&img=1&zoom=5&source=gbs_api\"},\"averageRating\":3.5,\"panelizationSummary\":{\"containsImageBubbles\":false,\"containsEpubBubbles\":false},\"publisher\":\"Graphia\",\"ratingsCount\":41,\"publishedDate\":\"2001-06\",\"categories\":[\"Juvenile Fiction\"],\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"0.3.2.0.preview.0\",\"authors\":[\"Diane Duane\"],\"infoLink\":\"http://books.google.com/books?id=sbgn_U3h77AC&dq=isbn:9780152162504&hl=&source=gbs_api\"},\"etag\":\"l80S7LwjPoQ\",\"id\":\"sbgn_U3h77AC\",\"accessInfo\":{\"accessViewStatus\":\"NONE\",\"country\":\"US\",\"viewability\":\"NO_PAGES\",\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://play.google.com/books/reader?id=sbgn_U3h77AC&hl=&printsec=frontcover&source=gbs_api\",\"epub\":{\"isAvailable\":false},\"publicDomain\":false,\"quoteSharingAllowed\":false,\"embeddable\":false,\"textToSpeechPermission\":\"ALLOWED\"},\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/sbgn_U3h77AC\"}]}",json3.toString());
    }
    @Test(expected = EmptyQueryException.class)
    public void testJsonFromQueryGoogleBooksException() throws IOException, EmptyQueryException, notFoundException {
        BookInfo bookInfo = new BookInfo();
        JSONObject json4 = bookInfo.getJsonFromQueryGoogle("","","");
    }

    @Test
    public void testScrapeRecs() throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
        BookInfo bookInfo = new BookInfo();
        List<String> listOfRecs = bookInfo.getRecommendedBooks("0152047387");
        List<String> expectedList = new ArrayList<>();
        expectedList.add("Deep Secret (Magids, #1)");
        expectedList.add("Sandry's Book (Circle of Magic, #1)");
        expectedList.add("Dragon's Blood (Pit Dragon Chronicles, #1)");
        expectedList.add("Calling on Dragons (Enchanted Forest Chronicles, #3)");
        expectedList.add("The Secret Country (The Secret Country, #1)");
        expectedList.add("Heir Apparent (Rasmussem Corporation, #2)");
        Assert.assertEquals(expectedList, listOfRecs);
    }
    @Test
    public void testScrapeLink() throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
        //this tests that "Harry Potter and the Sorcerer's Stone" returns all **possible** book isbns (some bookInfo sources don't list the isbns easily)
        BookInfo bookInfo = new BookInfo();
        List<String> listOfISBNs = bookInfo.getISBNFromQuery("Harry Potter and the Sorcerer's Stone");
        List<String> customListOfISBNs = new ArrayList<>();
        customListOfISBNs.add("9780439554930");
        customListOfISBNs.add("2940000829790");
        customListOfISBNs.add("B005E0QXGG");
        customListOfISBNs.add("9780439211161");
        customListOfISBNs.add("9781548602642");
        customListOfISBNs.add("9780439294829");
        customListOfISBNs.add("9785550118191");
        customListOfISBNs.add("2940000052372");
        customListOfISBNs.add("9780757991714");
        customListOfISBNs.add("9780887246586");
        customListOfISBNs.add("2940012085788");
        customListOfISBNs.add("9781602491793");
        customListOfISBNs.add("9781934840573");
        Assert.assertEquals(customListOfISBNs, listOfISBNs);
    }

}
