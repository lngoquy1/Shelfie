package edu.swarthmore.cs.cs71.shelved.model.simple;

import edu.swarthmore.cs.cs71.shelved.model.api.Genre;

public class SimpleGenre implements Genre {
    private String genre;
    public String header = this.getClass().getSimpleName();

    public SimpleGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre1 = (Genre) o;

        return genre != null ? genre.equals(genre1.getGenre()) : genre1.getGenre() == null;
    }

    @Override
    public int hashCode() {
        return genre != null ? genre.hashCode() : 0;
    }
}
