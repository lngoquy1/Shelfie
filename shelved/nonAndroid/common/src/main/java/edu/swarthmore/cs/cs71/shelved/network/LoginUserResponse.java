package edu.swarthmore.cs.cs71.shelved.network;

public class LoginUserResponse extends ResponseMessage {
    private int id;
    public LoginUserResponse() {
        super(true);
    }

    public LoginUserResponse( int id) {
        super(true);
        this.id = id;
    }

    public int getUserId() {
        return id;
    }
}
