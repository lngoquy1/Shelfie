package edu.swarthmore.cs.cs71.shelved;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class HibGenre implements Genre {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String genre;

    public HibGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String getGenre() {
        return genre;
    }
}
