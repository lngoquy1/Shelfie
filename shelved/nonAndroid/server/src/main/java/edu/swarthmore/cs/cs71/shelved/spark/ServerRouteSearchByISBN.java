package edu.swarthmore.cs.cs71.shelved.spark;

import edu.swarthmore.cs.cs71.shelved.model.bookData.BookInfo;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.network.InvalidSearchResponse;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;
import edu.swarthmore.cs.cs71.shelved.network.ValidSearchResponseISBN;
import org.hibernate.SessionFactory;
import spark.Request;
import spark.Response;

public class ServerRouteSearchByISBN extends ServerRoute {
    public ServerRouteSearchByISBN(SessionFactory sf) {
        super(sf);
    }

    @Override
    protected ResponseMessage execute(Request request, Response response) {
        try {
            String isbn = request.queryParams("ISBN");
            System.out.println("Search isbn: " + isbn);
            BookInfo bookInfo = new BookInfo();
            SimpleBook simpleBook = bookInfo.populateSimpleBookFromISBN(isbn);
            System.out.println("WE GOT HERE. Our book has title: " + simpleBook.getTitle().getTitle());
            return new ValidSearchResponseISBN(simpleBook);
        } catch (Exception e) {
            return new InvalidSearchResponse("Invalid search response");
        }
    }
}