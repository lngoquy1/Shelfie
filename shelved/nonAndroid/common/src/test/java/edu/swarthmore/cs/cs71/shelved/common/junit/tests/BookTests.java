package edu.swarthmore.cs.cs71.shelved.common.junit.tests;

import edu.swarthmore.cs.cs71.shelved.Author;
import edu.swarthmore.cs.cs71.shelved.Genre;
import edu.swarthmore.cs.cs71.shelved.SimpleBook;
import org.junit.Assert;
import org.junit.Test;

public class BookTests {

    @Test
    public void testGetAuthor() {
        SimpleBook book = new SimpleBook("JK Rowling", "Fiction", "Harry Potter",1, "HPPub");
        Author author = new Author("JK Rowling");
        Assert.assertEquals(author, book.getAuthor());
    }

    @Test
    public void testGetGenre() {
        SimpleBook book = new SimpleBook("JK Rowling", "Fiction", "Harry Potter",1, "HPPub");
        Genre genre = new Genre("Fiction");
        Assert.assertEquals(genre, book.getGenre());
    }

}
