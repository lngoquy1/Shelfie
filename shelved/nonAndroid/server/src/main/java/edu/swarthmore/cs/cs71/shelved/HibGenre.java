package edu.swarthmore.cs.cs71.shelved;

import javax.persistence.*;

@Entity
@Table(name="genre")
public class HibGenre implements Genre {
    @Id
    @Column(name="genre_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="genreName")
    private String genre;

    public HibGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String getGenre() {
        return this.genre;
    }
}
