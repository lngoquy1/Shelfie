package edu.swarthmore.cs.cs71.shelved;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="book_title")
public class HibTitle implements Title {
    private int id;

    private String title;

    public HibTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
