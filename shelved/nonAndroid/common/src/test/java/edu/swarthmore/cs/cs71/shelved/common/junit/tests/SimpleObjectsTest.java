package edu.swarthmore.cs.cs71.shelved.common.junit.tests;

import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.model.simple.*;
import org.junit.Assert;
import org.junit.Test;

public class SimpleObjectsTest {

    // SimpleAuthor Tests
    @Test
    public void getAuthorFields() {
        SimpleAuthor author = new SimpleAuthor("Martin Fowler");

        Assert.assertEquals("Martin Fowler", author.getAuthorName());
        Assert.assertEquals("Fowler", author.getLastName());
        Assert.assertNotEquals("martin fowler", author.getAuthorName());
        Assert.assertNotEquals("fowler", author.getLastName());
    }


    // SimpleBookShelf Tests and SimpleRowShelf tests
    @Test
    public void getBookShelfFields() {
        // make bookshelf
        SimpleBookShelf bookshelf = new SimpleBookShelf();

        // make one row
        SimpleRowShelf row1 = new SimpleRowShelf();

        // add row to bookshelf
        bookshelf.addRowShelf(row1);

        // test that bookshelf believes it has one row
        Assert.assertEquals(1, bookshelf.getNumRows());
        Assert.assertNotEquals(0, bookshelf.getNumRows());


        // create book
        SimpleBook book = new SimpleBook(); // ("JK Rowling", "Fiction", "Harry Potter",1, "HPPub");
        book.setAuthor("JK Rowling");
        book.setGenre("Fiction");
        book.setPages(1);
        book.setTitle("Harry Potter");
        book.setPublisher("HPPub");

        // set shelved book to be SimpleBook book
        SimpleShelvedBook shelvedBook = new SimpleShelvedBook();
        shelvedBook.setBook(book);

        // add this book to the row
        row1.addBook(shelvedBook);


        // test that row believes it has one book
        Assert.assertEquals(1, row1.getAllBooks().size());
        Assert.assertNotEquals(0, row1.getAllBooks().size());

        SimpleShelvedBook bookFromRow = row1.getBook(0);

        SimpleBook bookFromShelvedBook = bookFromRow.getBook();

        // test that the simplebook retrieved from the row is the same as the one we put on it
        Assert.assertEquals(book.getAuthor(), bookFromShelvedBook.getAuthor());
        Assert.assertEquals(book.getTitle(), bookFromShelvedBook.getTitle());


        // remove book from row
        row1.removeBook(shelvedBook);

        // test that row is now empty
        Assert.assertEquals(0, row1.getAllBooks().size());

        // remove row1 from bookshelf
        bookshelf.removeRowShelf(row1);

        // test that bookshelf is now empty
        Assert.assertEquals(0, bookshelf.getNumRows());
    }


    // SimpleShelvedBook Tests
    @Test
    public void getSimpleShelvedBookFields() {
        // create book
        SimpleBook book = new SimpleBook(); // ("JK Rowling", "Fiction", "Harry Potter",1, "HPPub");
        book.setAuthor("JK Rowling");
        book.setGenre("Fiction");
        book.setPages(1);
        book.setTitle("Harry Potter");
        book.setPublisher("HPPub");

        // set shelved book to be SimpleBook book
        SimpleShelvedBook shelvedBook = new SimpleShelvedBook();
        shelvedBook.setBook(book);

        shelvedBook.setBookMark(85);

        Assert.assertEquals(false, shelvedBook.isForSale());
        Assert.assertEquals(false, shelvedBook.isForLend());

        shelvedBook.setForLend(true);
        shelvedBook.setForSale(true);

        Assert.assertEquals(85, shelvedBook.getBookMark());
        Assert.assertNotEquals(86, shelvedBook.getBookMark());

        Assert.assertEquals(true, shelvedBook.isForLend());
        Assert.assertEquals(true, shelvedBook.isForSale());

    }
}