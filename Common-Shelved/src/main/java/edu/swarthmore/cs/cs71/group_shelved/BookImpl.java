package edu.swarthmore.cs.cs71.group_shelved;

import java.util.Dictionary;
import java.util.List;

public class BookImpl implements Book {
    Author author;
    Genre genre;
    Title title;
    int pages;
    Publisher publisher;

    public BookImpl(String author, String genre, String title, int pages, String publisher) {
        this.author = new Author(author);
        this.genre = new Genre(genre);
        this.title = new Title(title);
        this.pages = pages;
        this.publisher = new Publisher(publisher);
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
