package edu.swarthmore.cs.cs71.group_shelved.iteration3;

public class Author {
    private String authorName;
    private String lastName;

    public Author(String authorName) {
        this.authorName = authorName;
        this.lastName = authorName.substring(authorName.lastIndexOf(" ") +1);
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getLastName() {
        return lastName;
    }
}
