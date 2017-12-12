package edu.swarthmore.cs.cs71.shelved.network.AddBookToReadingList;

import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;

public class InvalidBookAddedToListResponse extends ResponseMessage {
    private String error_message;

    public InvalidBookAddedToListResponse(){
        super(true);
    }

    public InvalidBookAddedToListResponse(String error_message) {
        super(true);
        this.error_message = error_message;
    }
}
