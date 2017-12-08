package edu.swarthmore.cs.cs71.shelved.network;

public class InvalidReadingListUpdatedResponse extends ResponseMessage {
    private String error_message;

    public InvalidReadingListUpdatedResponse(){
        super(true);
    }

    public InvalidReadingListUpdatedResponse(String error_message) {
        super(true);
        this.error_message = error_message;
    }
}
