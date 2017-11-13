package edu.swarthmore.cs.cs71.shelved.common.junit.tests;

import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleAuthor;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleGenre;
import org.junit.Assert;
import org.junit.Test;

public class BookTests {

    @Test
    public void testGetAuthor() {
        SimpleBook book = new SimpleBook(); // ("JK Rowling", "Fiction", "Harry Potter",1, "HPPub");
        book.setAuthor("JK Rowling");
        book.setGenre("Fiction");
        book.setPages(1);
        book.setTitle("Harry Potter");
        book.setPublisher("HPPub");

        SimpleAuthor author = new SimpleAuthor("JK Rowling");
        Assert.assertEquals(author, book.getAuthor());
    }

    @Test
    public void testGetGenre() {
        SimpleBook book = new SimpleBook(); // ("JK Rowling", "Fiction", "Harry Potter",1, "HPPub");
        book.setAuthor("JK Rowling");
        book.setGenre("Fiction");
        book.setPages(1);
        book.setTitle("Harry Potter");
        book.setPublisher("HPPub");
        SimpleGenre genre = new SimpleGenre("Fiction");
        Assert.assertEquals(genre, book.getGenre());
    }


}
