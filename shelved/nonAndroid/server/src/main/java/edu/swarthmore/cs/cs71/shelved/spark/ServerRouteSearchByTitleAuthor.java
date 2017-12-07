package edu.swarthmore.cs.cs71.shelved.spark;

import edu.swarthmore.cs.cs71.shelved.model.bookData.BookInfo;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.network.InvalidSearchResponse;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;
import edu.swarthmore.cs.cs71.shelved.network.ValidSearchResponseTitleAuthor;
import org.hibernate.SessionFactory;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;

public class ServerRouteSearchByTitleAuthor extends ServerRoute {
    public ServerRouteSearchByTitleAuthor(SessionFactory sf) {
        super(sf);
    }

    @Override
    protected ResponseMessage execute(Request request, Response response) {
        try {
            String title = request.queryParams("title");
            String author = request.queryParams("author");
            System.out.println("Search title: "+title+"Search author: " + author);
            BookInfo bookInfo = new BookInfo();
            List<String> ISBNs = bookInfo.getISBNsFromTitleAndOrAuthor(title,author);
            List<SimpleBook> possibleBooks = new ArrayList<>();
            for (String isbn:ISBNs){
                SimpleBook simpleBook = new SimpleBook();
                simpleBook.setTitle(bookInfo.getTitleFromISBN(isbn));
                simpleBook.setAuthor(bookInfo.getAuthorFromISBN(isbn));
                simpleBook.setPublisher(bookInfo.getPublisherFromISBN(isbn));
                simpleBook.setPages(bookInfo.getNumPagesFromISBN(isbn));
                simpleBook.setGenre(bookInfo.getGenreFromISBN(isbn));
                possibleBooks.add(simpleBook);
            }
            return new ValidSearchResponseTitleAuthor(possibleBooks);

        } catch (Exception e) {
            return new InvalidSearchResponse("Invalid search response");
        }
    }
}
