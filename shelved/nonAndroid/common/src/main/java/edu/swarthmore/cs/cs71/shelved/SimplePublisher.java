package edu.swarthmore.cs.cs71.shelved;

public class SimplePublisher implements Publisher {
    private String publisher;

    public SimplePublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }
}
