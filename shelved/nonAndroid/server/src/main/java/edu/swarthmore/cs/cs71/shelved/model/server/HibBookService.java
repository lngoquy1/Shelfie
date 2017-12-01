package edu.swarthmore.cs.cs71.shelved.model.server;

import edu.swarthmore.cs.cs71.shelved.model.bookData.EmptyQueryException;
import edu.swarthmore.cs.cs71.shelved.model.bookData.NotFoundException;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.spark.PersistenceUtils;
import edu.swarthmore.cs.cs71.shelved.model.bookData.BookInfo;
import org.hibernate.SessionFactory;
import org.xml.sax.SAXException;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.persistence.EntityManager;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HibBookService {
    public HibBook createBook(String title, String author){ //, String genre, int pages, String publisher
        HibBook newBook = new HibBook();
        newBook.setAuthor(author);
        newBook.setTitle(title);
//        newBook.setGenre(genre);
//        newBook.setPages(pages);
//        newBook.setPublisher(publisher);
        System.out.println("start");
        PersistenceUtils.ENTITY_MANAGER.get().persist(newBook);
        return newBook;
    }

    public HibBook createBookFromISBN(String ISBN) throws NotFoundException, ParserConfigurationException, IOException, XPathExpressionException, SAXException, EmptyQueryException { //, String genre, int pages, String publisher
        BookInfo bookInfo = new BookInfo();
        String title = bookInfo.getTitleFromISBN(ISBN);
        String author = bookInfo.getAuthorFromISBN(ISBN);
        String publisher = bookInfo.getPublisherFromISBN(ISBN);
        String genre = bookInfo.getGenreFromISBN(ISBN);
        int pages = Integer.parseInt(bookInfo.getNumPagesFromISBN(ISBN));
        HibBook newBook = new HibBook();
        newBook.setAuthor(author);
        newBook.setTitle(title);
        newBook.setGenre(genre);
        newBook.setPages(pages);
        newBook.setPublisher(publisher);
        System.out.println("start");
        PersistenceUtils.ENTITY_MANAGER.get().persist(newBook);
        return newBook;
    }

    public List<SimpleBook> getAllBooks(SessionFactory sf){
        EntityManager session = sf.createEntityManager();
        List<SimpleBook> simpleBooks = new ArrayList<>();
        List<HibBook> hibBooks = session.createQuery("FROM HibBook").getResultList();
        for (HibBook book:hibBooks){
            // TODO: Need to get all fields in the future
            SimpleBook newSimpleBook = new SimpleBook();
            newSimpleBook.setTitle(book.getTitle().getTitle());
            newSimpleBook.setAuthor(book.getAuthor().getAuthorName());
            simpleBooks.add(newSimpleBook);
        }
        return simpleBooks;
    }

}
