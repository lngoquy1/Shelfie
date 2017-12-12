package edu.swarthmore.cs.cs71.shelved.spark;


import edu.swarthmore.cs.cs71.shelved.model.server.HibBookService;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;
import edu.swarthmore.cs.cs71.shelved.network.BookListUpdate.ValidBookListUpdateResponse;
import org.hibernate.SessionFactory;
import spark.Request;
import spark.Response;

import java.util.List;

public class ServerRouteUpdateBookList extends ServerRoute{
    public ServerRouteUpdateBookList(SessionFactory sf) {
        super(sf);
    }

    @Override
    protected ResponseMessage execute(Request request, Response response) {
        HibBookService service = new HibBookService();
        List<SimpleBook> simpleBooks = service.getAllBooks(getSf());
        return new ValidBookListUpdateResponse(simpleBooks);
    }
}
