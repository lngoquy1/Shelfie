package edu.swarthmore.cs.cs71.shelved.network;

import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;

public class ValidBookAddedResponse extends ResponseMessage {
    private SimpleBook book;
    private ValidBookAddedResponse() { super(true); }
    public ValidBookAddedResponse(SimpleBook book) {
        super(true);
        this.book = book;
    }

    public SimpleBook getBook() {
        return book;
    }
}
