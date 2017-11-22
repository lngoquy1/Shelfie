package edu.swarthmore.cs.cs71.shelved.spark.junit.tests;

import edu.swarthmore.cs.cs71.shelved.model.server.HibBook;
import org.junit.Assert;
import org.junit.Test;

public class TestHibBook {
    @Test
    public void TestNewUser(){
        HibBook book = new HibBook();
        book.setAuthor("Martin Fowler");
        book.setTitle("Refactoring");
        book.setPages(13);
        book.setGenre("Computer Science");
        book.setPublisher("Unknown");

        Assert.assertEquals("Martin Fowler", book.getAuthor().getAuthorName());
        Assert.assertEquals("Refactoring", book.getTitle().getTitle());
        Assert.assertEquals(13, book.getPages());
        Assert.assertEquals("Computer Science", book.getGenre().getGenre());
        Assert.assertEquals("Unknown", book.getPublisher().getPublisher());
    }
} 
