package edu.swarthmore.cs.cs71.shelved.network.Login;

import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;

public class InvalidLoginUserResponse extends ResponseMessage {
    private String error_message;
    public InvalidLoginUserResponse() {
        super(true);
    }

    public InvalidLoginUserResponse(String error_message) {
        super(true);
        this.error_message = error_message;
    }
}
