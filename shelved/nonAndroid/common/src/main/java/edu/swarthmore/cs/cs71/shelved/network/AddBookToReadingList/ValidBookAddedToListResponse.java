package edu.swarthmore.cs.cs71.shelved.network.AddBookToReadingList;

import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;

public class ValidBookAddedToListResponse extends ResponseMessage {
    private SimpleBook book;
    private ValidBookAddedToListResponse() { super(true); }
    public ValidBookAddedToListResponse(SimpleBook book) {
        super(true);
        this.book = book;
    }

    public SimpleBook getBook() { return book; }
}
