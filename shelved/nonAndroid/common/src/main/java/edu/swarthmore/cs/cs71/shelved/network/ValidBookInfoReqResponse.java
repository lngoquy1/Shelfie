package edu.swarthmore.cs.cs71.shelved.network;

import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleAuthor;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleTitle;

public class ValidBookInfoReqResponse extends ResponseMessage {
//    private SimpleAuthor author;
//    private SimpleTitle title;
    private String ISBN;
    private ValidBookInfoReqResponse() { super(true); }

//    public ValidBookInfoReqResponse(SimpleTitle title, SimpleAuthor author) {
//        super(true);
//        this.author = author;
//        this.title = title;
//    }

    public ValidBookInfoReqResponse(String ISBN) {
        super(true);
        this.ISBN = ISBN;
    }

//    public SimpleAuthor getAuthor() {
//        return author;
//    }
//
//    public SimpleTitle getTitle() {
//        return title;
//    }

    public String getISBN() {
        return ISBN;
    }
}
