package edu.swarthmore.cs.cs71.shelved.spark;

import edu.swarthmore.cs.cs71.shelved.model.bookData.BookInfo;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.network.*;
import org.hibernate.SessionFactory;
import spark.Request;
import spark.Response;

import java.util.List;

public class ServerRouteRecommendedBooks extends ServerRoute {

    public ServerRouteRecommendedBooks(SessionFactory sf) {
        super(sf);
    }

    @Override
    protected ResponseMessage execute(Request request, Response response) {
        try {
            String isbn = request.queryParams("isbn");
            System.out.println("Search isbn: " + isbn);
            BookInfo bookInfo = new BookInfo();
            List<SimpleBook> simpleBookList = bookInfo.getRecommendedBooksFromISBN(isbn);
            System.out.println("REC BOOK LIST: " + simpleBookList);
            return new ValidRecBookResponse(simpleBookList);
        } catch (Exception e) {
            System.out.println("SENDING INVALID BOOK RESPONSE!!!");
            return new InvalidRecBookResponse("Invalid recommended book response");
        }
    }
}
