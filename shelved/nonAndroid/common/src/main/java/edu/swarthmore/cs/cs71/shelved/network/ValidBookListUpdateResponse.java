package edu.swarthmore.cs.cs71.shelved.network;

import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;

import java.util.List;

public class ValidBookListUpdateResponse extends ResponseMessage {
    private List<SimpleBook> bookList;
    private ValidBookListUpdateResponse(){super(true);}
    public ValidBookListUpdateResponse(List<SimpleBook> bookList){
        super(true);
        this.bookList = bookList;
    }

    public List<SimpleBook> getBookList() {
        return bookList;
    }
}
