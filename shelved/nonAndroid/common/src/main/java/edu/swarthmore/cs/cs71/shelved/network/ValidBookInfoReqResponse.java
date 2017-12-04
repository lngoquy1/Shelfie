package edu.swarthmore.cs.cs71.shelved.network;

import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleAuthor;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleTitle;

public class ValidBookInfoReqResponse extends ResponseMessage {
//    private SimpleAuthor author;
//    private SimpleTitle title;
    private SimpleBook book;
    private ValidBookInfoReqResponse() { super(true); }

//    public ValidBookInfoReqResponse(SimpleTitle title, SimpleAuthor author) {
//        super(true);
//        this.author = author;
//        this.title = title;
//    }

    public ValidBookInfoReqResponse(SimpleBook book) {
        super(true);
        this.book = book;
    }

//    public SimpleAuthor getAuthor() {
//        return author;
//    }
//
//    public SimpleTitle getTitle() {
//        return title;
//    }

    public SimpleBook getBook() {
        return book;
    }
}
