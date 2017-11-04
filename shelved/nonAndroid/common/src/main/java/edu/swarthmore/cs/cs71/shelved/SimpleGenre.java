package edu.swarthmore.cs.cs71.shelved;

public class SimpleGenre implements Genre {
    private String genre;

    public SimpleGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}
