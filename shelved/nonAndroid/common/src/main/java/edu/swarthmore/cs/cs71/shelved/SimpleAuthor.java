package edu.swarthmore.cs.cs71.shelved;

public class SimpleAuthor implements Author {
    private String fullName;
    private String lastName;

    public SimpleAuthor(String authorName) {
        this.fullName = authorName;
        this.lastName = authorName.substring(authorName.lastIndexOf(" ") +1);
    }

    public String getFullName() {
        return fullName;
    }

    public String getLastName() {
        return lastName;
    }
}
