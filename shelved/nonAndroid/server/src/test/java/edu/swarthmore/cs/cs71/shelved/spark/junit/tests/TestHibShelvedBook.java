package edu.swarthmore.cs.cs71.shelved.spark.junit.tests;

import edu.swarthmore.cs.cs71.shelved.model.server.HibBook;
import edu.swarthmore.cs.cs71.shelved.model.server.HibShelvedBook;
import org.junit.Assert;
import org.junit.Test;

public class TestHibShelvedBook {
    @Test
    public void TestNewShelvedBook() {
        HibBook book = new HibBook();
        book.setAuthor("Martin Fowler");
        book.setTitle("Refactoring");
        book.setPages(13);
        book.setGenre("Computer Science");
        book.setPublisher("Unknown");

        HibShelvedBook shelvedBook = new HibShelvedBook();
        shelvedBook.setBook(book);
        shelvedBook.setBookMark(11);
        shelvedBook.setForLend(true);
        shelvedBook.setForSale(false);

        Assert.assertEquals(book, shelvedBook.getBook());
        Assert.assertEquals(11, shelvedBook.getBookMark());
        Assert.assertEquals(true, shelvedBook.isForLend());
        Assert.assertNotEquals(false, shelvedBook.isForLend());
        Assert.assertEquals(false, shelvedBook.isForSale());
        Assert.assertNotEquals(true, shelvedBook.isForSale());
    }
}
