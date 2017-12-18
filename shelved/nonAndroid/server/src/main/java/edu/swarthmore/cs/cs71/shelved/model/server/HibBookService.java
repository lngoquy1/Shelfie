package edu.swarthmore.cs.cs71.shelved.model.server;

import edu.swarthmore.cs.cs71.shelved.model.bookData.EmptyQueryException;
import edu.swarthmore.cs.cs71.shelved.model.bookData.NotFoundException;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.spark.PersistenceUtils;
import edu.swarthmore.cs.cs71.shelved.model.bookData.BookInfo;
import org.hibernate.SessionFactory;
import org.xml.sax.SAXException;

import javax.persistence.EntityManager;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HibBookService {
    public HibBook createBook(SessionFactory sf, String userID, String title, String author) throws NotFoundException, ParserConfigurationException, IOException, XPathExpressionException, SAXException, EmptyQueryException { //, String genre, int pages, String publisher
        HibBook newBook = new HibBook();
        newBook.setTitle(title);
        newBook.setAuthor(author);
//        BookInfo bookInfo = new BookInfo();
//        SimpleBook book = bookInfo.populateSimpleBookFromTitleAndOrAuthor(title, author);
//        newBook.setTitle(book.getTitle().getTitle());
//        newBook.setAuthor(book.getAuthor().getAuthorName());
//        newBook.setGenre(book.getGenre().getGenre());
//        newBook.setPages(book.getPages());
//        newBook.setPublisher(book.getPublisher().getPublisher());
        // TODO: Adding this book to an user
        HibUser currentUser = new HibUserService().getUserByID(sf, Integer.valueOf(userID));
        System.out.println("Current user: "+currentUser.getName());
        currentUser.addBook(newBook);
        PersistenceUtils.ENTITY_MANAGER.get().persist(newBook);
        System.out.println("finish persisting newBook");
        PersistenceUtils.ENTITY_MANAGER.get().merge(currentUser);
        System.out.println("finish merging currentUser");
        return newBook;
    }

    public HibBook createBookFromISBN(String ISBN) throws NotFoundException, ParserConfigurationException, IOException, XPathExpressionException, SAXException, EmptyQueryException { //, String genre, int pages, String publisher
        BookInfo bookInfo = new BookInfo();
        SimpleBook simpleBook = bookInfo.populateSimpleBookFromISBN(ISBN);
        String title = simpleBook.getTitle().getTitle();
        String author = simpleBook.getAuthor().getAuthorName();
        String publisher = simpleBook.getPublisher().getPublisher();
        String genre = simpleBook.getGenre().getGenre();
        String isbn = simpleBook.getISBN().getISBN();
        int pages = simpleBook.getPages();
        String imageUrl = simpleBook.getImageUrl();
        HibBook newBook = new HibBook();
        newBook.setAuthor(author);
        newBook.setTitle(title);
        newBook.setGenre(genre);
        newBook.setPages(pages);
        newBook.setPublisher(publisher);
        newBook.setISBN(isbn);
        newBook.setImageUrl(imageUrl);
        PersistenceUtils.ENTITY_MANAGER.get().persist(newBook);
        return newBook;
    }

    public List<SimpleBook> getAllBooks(SessionFactory sf, String userID){
        EntityManager session = sf.createEntityManager();
        HibUser currentUser = new HibUserService().getUserByID(sf, Integer.valueOf(userID));
        List<HibBook> hibBooks = currentUser.getUserBooks();
        List<SimpleBook> simpleBooks = new ArrayList<>();
        for (HibBook book:hibBooks){
            // TODO: Need to get all fields in the future
            SimpleBook newSimpleBook = new SimpleBook();
            newSimpleBook.setTitle(book.getTitle().getTitle());
            newSimpleBook.setAuthor(book.getAuthor().getAuthorName());
            newSimpleBook.setGenre(book.getGenre().getGenre());
            newSimpleBook.setPages(book.getPages());
            newSimpleBook.setPublisher(book.getPublisher().getPublisher());
            newSimpleBook.setISBN(book.getISBN().getISBN());
            newSimpleBook.setImageUrl(book.getImageUrl());
            simpleBooks.add(newSimpleBook);
        }
        return simpleBooks;
    }

}
