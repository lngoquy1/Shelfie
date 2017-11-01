package edu.swarthmore.cs.cs71.group_shelved.common;

public class Author {
    private String fullName;
    private String lastName;

    public Author(String authorName) {
        this.fullName = authorName;
        this.lastName = authorName.substring(authorName.lastIndexOf(" ") +1);
    }

    public String getAuthorName() {
        return fullName;
    }

    public String getLastName() {
        return lastName;
    }
}
