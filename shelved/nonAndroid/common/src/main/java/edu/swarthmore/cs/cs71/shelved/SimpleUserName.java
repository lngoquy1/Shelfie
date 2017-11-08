package edu.swarthmore.cs.cs71.shelved;

public class SimpleUserName implements UserName{
    private String userName;

    public SimpleUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getUserName() {
        return this.userName;
    }
}
