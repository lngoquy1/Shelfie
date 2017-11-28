package edu.swarthmore.cs.cs71.shelved.network;

public class InvalidBookAddedResponse extends ResponseMessage {
    private String error_message;

    public InvalidBookAddedResponse(){
        super(true);
    }

    public InvalidBookAddedResponse(String error_message) {
        super(true);
        this.error_message = error_message;
    }
}
