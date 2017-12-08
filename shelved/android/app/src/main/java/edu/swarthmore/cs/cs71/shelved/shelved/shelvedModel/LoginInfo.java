package edu.swarthmore.cs.cs71.shelved.shelved.shelvedModel;

public class LoginInfo {
    private int userID;
    private String token;

    public LoginInfo(int userID, String token) {
        this.userID = userID;
        this.token = token;
    }

    public int getUserID() {
        return userID;
    }

    public String getToken() {
        return token;
    }
}
