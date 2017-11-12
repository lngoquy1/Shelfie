package edu.swarthmore.cs.cs71.shelved.simple;

import edu.swarthmore.cs.cs71.shelved.api.UserName;

public class SimpleUserName implements UserName {
    private String userName;

    public SimpleUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getUserName() {
        return this.userName;
    }
}
