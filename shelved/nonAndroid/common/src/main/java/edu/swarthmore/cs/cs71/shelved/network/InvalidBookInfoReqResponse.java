package edu.swarthmore.cs.cs71.shelved.network;

public class InvalidBookInfoReqResponse extends ResponseMessage {
    private String error_message;

    public InvalidBookInfoReqResponse(){
        super(true);
    }

    public InvalidBookInfoReqResponse(String error_message) {
        super(true);
        this.error_message = error_message;
    }
}
