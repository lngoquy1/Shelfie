package edu.swarthmore.cs.cs71.shelved.network.ListAdded;

import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;

public class InvalidListAddedResponse extends ResponseMessage {
    private String error_message;

    public InvalidListAddedResponse() { super(true); }

    public InvalidListAddedResponse(String error_message) {
        super(true);
        this.error_message = error_message;
    }
}
