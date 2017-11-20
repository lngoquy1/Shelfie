package edu.swarthmore.cs.cs71.shelved.network;

public class ValidLoginUserResponse extends ResponseMessage {
    private int id;
    public ValidLoginUserResponse() {
        super(true);
    }

    public ValidLoginUserResponse(int id) {
        super(true);
        this.id = id;
        System.out.println(this.id);
    }

    public int getUserId() {
        return id;
    }
}
