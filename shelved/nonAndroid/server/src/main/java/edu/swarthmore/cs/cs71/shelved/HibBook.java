package edu.swarthmore.cs.cs71.shelved;

import javax.persistence.*;
import java.util.Dictionary;
import java.util.List;

@Entity
@Table(name="book")
public class HibBook implements Book {
    private int id;
    private Author author;
    private Genre genre;
    private Title title;
    private int pages;
    private Publisher publisher;

    public HibBook(Author author, Genre genre, Title title, int pages, Publisher publisher) {
        this.author = author;
        this.genre = genre;
        this.title = title;
        this.pages = pages;
        this.publisher = publisher;
    }

    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Author getAuthor() {
        return null;
    }

    @Override
    public Genre getGenre() {
        return null;
    }

    @Override
    public Title getTitle() {
        return null;
    }

    @Override
    public int getPages() {
        return 0;
    }

    @Override
    public Publisher getPublisher() {
        return null;
    }

    @Override
    public List<Book> getRecBooks() {
        return null;
    }

    @Override
    public Dictionary<String, Double> getPrices() {
        return null;
    }
}
