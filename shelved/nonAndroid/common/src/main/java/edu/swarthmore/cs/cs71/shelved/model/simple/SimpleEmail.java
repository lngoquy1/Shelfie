package edu.swarthmore.cs.cs71.shelved.model.simple;

import edu.swarthmore.cs.cs71.shelved.model.api.Email;

public class SimpleEmail implements Email {
    private String userName;

    public SimpleEmail(String userName) {
        this.userName = userName;
    }

    @Override
    public String getEmail() {
        return this.userName;
    }
}
