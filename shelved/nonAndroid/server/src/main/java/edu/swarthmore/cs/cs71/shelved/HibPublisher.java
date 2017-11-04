package edu.swarthmore.cs.cs71.shelved;

public class HibPublisher implements Publisher {
    private String publisher;

    public HibPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String getPublisher() {
        return publisher;
    }
}
