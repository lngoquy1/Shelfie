package edu.swarthmore.cs.cs71.shelved.spark.junit.tests;

import edu.swarthmore.cs.cs71.shelved.model.server.HibBook;
import edu.swarthmore.cs.cs71.shelved.model.server.HibRowShelf;
import edu.swarthmore.cs.cs71.shelved.model.server.HibShelvedBook;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestHibRowShelf {
    @Test
    public void TestNewRowShelf() {
        HibRowShelf shelf = new HibRowShelf();

        HibBook book = new HibBook();
        book.setAuthor("Martin Fowler");
        book.setTitle("Refactoring");
        book.setPages(13);
        book.setGenre("Computer Science");
        book.setPublisher("Unknown");

        HibShelvedBook shelvedBook = new HibShelvedBook();
        shelvedBook.setBook(book);

        shelf.addBook(shelvedBook);

        // create list of books we expect to be in the row
        List<HibShelvedBook> expectedAllBooks = new ArrayList<>();
        expectedAllBooks.add(shelvedBook);

        // test to see if this list is what we expect, and if row can get book by position
        Assert.assertEquals(expectedAllBooks, shelf.getAllBooks());
        Assert.assertEquals(shelvedBook, shelf.getBook(0));

        // remove book from row
        shelf.removeBook(shelvedBook);

        // remove book from expected list
        expectedAllBooks.remove(shelvedBook);

        // test that row is now empty
        Assert.assertEquals(expectedAllBooks, shelf.getAllBooks());
    }
}
