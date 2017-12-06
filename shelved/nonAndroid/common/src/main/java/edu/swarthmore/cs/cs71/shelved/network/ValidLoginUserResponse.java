package edu.swarthmore.cs.cs71.shelved.network;

public class ValidLoginUserResponse extends ResponseMessage {
    private int id;
    private String token;
    public ValidLoginUserResponse() {
        super(true);
    }

    public ValidLoginUserResponse(int id, String token) {
        super(true);
        this.id = id;
        this.token = token;
        System.out.println(this.id);
    }

    public int getUserId() {
        return id;
    }
}
