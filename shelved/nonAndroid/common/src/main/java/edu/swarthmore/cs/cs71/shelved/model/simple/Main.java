package edu.swarthmore.cs.cs71.shelved.model.simple;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;



//public class Main {
//    public static void main(String[] args) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
//        String ISBN = "0201485672";
//        Goodreads goodreads = new Goodreads();
//        List<String> listOfRecs = goodreads.getRecommendedBooks(ISBN);
//        String title = goodreads.getTitleFromISBN(ISBN);
//        System.out.println("\nIf you liked " + title + ", we recommend:");
//        for (String book:listOfRecs){
//            System.out.println("    "+book);
//        }
//    }
//
//}