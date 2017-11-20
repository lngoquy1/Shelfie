package edu.swarthmore.cs.cs71.shelved.network;

import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;

public class BookAddedResponse extends ResponseMessage {
    private SimpleBook book;
    private BookAddedResponse() { super(true); }
    public BookAddedResponse(SimpleBook book) {
        super(true);
        this.book = book;
    }

    public SimpleBook getBook() { return book; }
}
