package edu.swarthmore.cs.cs71.shelved.spark.junit.tests;

import edu.swarthmore.cs.cs71.shelved.model.server.HibBook;
import edu.swarthmore.cs.cs71.shelved.model.server.HibShelvedBook;
import org.junit.Assert;
import org.junit.Test;
import edu.swarthmore.cs.cs71.shelved.model.server.HibList;

public class TestHibList {
    @Test
    public void TestNewHibList() {
        HibList list = new HibList();
        list.setPublicStatus(true);
        list.resetName("My List");

        Assert.assertEquals("My List", list.getName());
        Assert.assertEquals(true, list.isPublicStatus());
        Assert.assertNotEquals(false, list.isPublicStatus());
    }

    @Test
    public void TestAddBook() {
        HibList list = new HibList();
        list.setPublicStatus(true);
        list.resetName("My List");

        HibBook book = new HibBook();
        book.setAuthor("Martin Fowler");
        book.setTitle("Refactoring");
        book.setPages(13);
        book.setGenre("Computer Science");
        book.setPublisher("Unknown");

        HibShelvedBook shelvedBook = new HibShelvedBook();
        shelvedBook.setBook(book);

        list.addBook(shelvedBook);
    }

    @Test
    public void TestAddRemoveBook() {
        HibList list = new HibList();
        list.setPublicStatus(true);
        list.resetName("My List");

        HibBook book = new HibBook();
        book.setAuthor("Martin Fowler");
        book.setTitle("Refactoring");
        book.setPages(13);
        book.setGenre("Computer Science");
        book.setPublisher("Unknown");

        HibShelvedBook shelvedBook = new HibShelvedBook();
        shelvedBook.setBook(book);

        list.addBook(shelvedBook);
        list.removeBook(shelvedBook);
    }

}
