package edu.swarthmore.cs.cs71.shelved.spark;

import edu.swarthmore.cs.cs71.shelved.model.server.HibBook;
import edu.swarthmore.cs.cs71.shelved.model.server.HibBookService;
import edu.swarthmore.cs.cs71.shelved.model.server.HibUserService;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.network.InvalidBookAddedResponse;
import edu.swarthmore.cs.cs71.shelved.network.ValidBookAddedResponse;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;
import org.hibernate.SessionFactory;
import spark.Request;
import spark.Response;

public class ServerRouteAddBook extends ServerRoute {

    public ServerRouteAddBook(SessionFactory sf) {
        super(sf);
    }

    @Override
    protected ResponseMessage execute(Request request, Response response) {
        
        try {
            HibBook newBook = new HibBookService().createBook(
                    request.queryParams("title"),
                    request.queryParams("author"));


            SimpleBook simpleBook = new SimpleBook();

            simpleBook.setTitle(newBook.getTitle().getTitle());
            simpleBook.setAuthor(newBook.getAuthor().getAuthorName());
            //        simpleBook.setGenre(newBook.getGenre().getGenre());
            //        simpleBook.setPages(newBook.getPages());
            //        simpleBook.setPublisher(newBook.getPublisher().getPublisher());
            return new ValidBookAddedResponse(simpleBook);
        } catch (Exception e){
            return new InvalidBookAddedResponse("Invalid book added response");
        }

    }

}
