//package edu.swarthmore.cs.cs71.shelved.spark;
//
//import edu.swarthmore.cs.cs71.shelved.model.server.HibAuthor;
//import edu.swarthmore.cs.cs71.shelved.model.server.HibBook;
//import edu.swarthmore.cs.cs71.shelved.model.server.HibBookService;
//import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
//import edu.swarthmore.cs.cs71.shelved.network.BookAddedResponse;
//import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;
//import org.hibernate.SessionFactory;
//import spark.Request;
//import spark.Response;
//
//public class ServerRouteAddBook extends ServerRoute {
//
//    public ServerRouteAddBook(SessionFactory sf) {
//        super(sf);
//    }
//
//    @Override
//    protected ResponseMessage execute(Request request, Response response) {
//
//        HibBook newBook = new HibBookService().createBook(
//                request.queryParams("author"),
//                request.queryParams("title"),
//                request.queryParams("genre"),
//                request.queryParams("pages"),
//                request.queryParams("publisher"));
//
//
//
//        SimpleBook simpleBook = new SimpleBook();
//
//        HibAuthor hibAuthor = newBook.getAuthor();
//        simpleBook.setAuthor(hibAuthor.getAuthorName());
//        simpleBook.setTitle(newBook.getTitle().getTitle());
//        simpleBook.setGenre(newBook.getGenre().getGenre());
//        simpleBook.setPages(newBook.getPages());
//        simpleBook.setPublisher(newBook.getPublisher().getPublisher());
//
//        return new BookAddedResponse(simpleBook);
//    }
//
//}
