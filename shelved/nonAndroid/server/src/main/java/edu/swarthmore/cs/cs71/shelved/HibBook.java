package edu.swarthmore.cs.cs71.shelved;

import edu.swarthmore.cs.cs71.group_shelved.common.*;

import javax.persistence.*;
import java.util.Dictionary;
import java.util.List;

@Entity
@Table(name="tbl_book")
public class HibBook implements Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    private Author author;
    @OneToOne
    private Genre genre;
    @OneToOne
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

    @Override
    @Id
    public int getId() {
        return this.id;
    }

    @Override
    public Author getAuthor() {
        return this.author;
    }

    @Override
    public Genre getGenre() {
        return this.genre;
    }

    @Override
    public Title getTitle() {
        return this.title;
    }

    @Override
    public int getPages() {
        return this.pages;
    }

    @Override
    public Publisher getPublisher() {
        return this.publisher;
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