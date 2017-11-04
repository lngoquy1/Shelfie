package edu.swarthmore.cs.cs71.shelved;

public class Genre {
    String genre;

    public Genre(String genre) {
        this.genre = genre;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre1 = (Genre) o;

        return genre != null ? genre.equals(genre1.genre) : genre1.genre == null;
    }

    @Override
    public int hashCode() {
        return genre != null ? genre.hashCode() : 0;
    }
}
