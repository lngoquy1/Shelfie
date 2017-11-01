package edu.swarthmore.cs.cs71.group_shelved.server;

import edu.swarthmore.cs.cs71.group_shelved.common.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Dictionary;
import java.util.List;

@Entity
@Table(name="tbl_book")
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