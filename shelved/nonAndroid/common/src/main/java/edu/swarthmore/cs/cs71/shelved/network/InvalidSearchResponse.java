package edu.swarthmore.cs.cs71.shelved.network;

public class InvalidSearchResponse extends ResponseMessage {
    private String error_message;

    public InvalidSearchResponse(){
        super(true);
    }

    public InvalidSearchResponse(String error_message) {
        super(true);
        this.error_message = error_message;
    }
}
