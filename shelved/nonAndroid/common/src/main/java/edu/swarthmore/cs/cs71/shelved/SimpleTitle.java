package edu.swarthmore.cs.cs71.shelved;

public class SimpleTitle implements Title{
    private String title;

    public SimpleTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
