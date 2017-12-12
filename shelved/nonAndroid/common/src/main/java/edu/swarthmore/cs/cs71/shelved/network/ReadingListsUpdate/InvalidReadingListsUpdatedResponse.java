package edu.swarthmore.cs.cs71.shelved.network.ReadingListsUpdate;

import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;

public class InvalidReadingListsUpdatedResponse extends ResponseMessage {
    private String error_message;

    public InvalidReadingListsUpdatedResponse(){
        super(true);
    }

    public InvalidReadingListsUpdatedResponse(String error_message) {
        super(true);
        this.error_message = error_message;
    }
}
