package edu.swarthmore.cs.cs71.shelved;

import javax.persistence.*;
import java.util.Dictionary;
import java.util.List;

@Entity
@Table(name="book")
public class HibBook implements Book {
    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private HibAuthor author;
    private HibGenre genre;
    private HibTitle title;
    private int pages;
    private HibPublisher publisher;

    public HibBook(){
    }


//    public int getId() {
//        return this.id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    @Override
    public void setAuthor(String author) {
        this.author = new HibAuthor(author);
    }

    @Override
    public void setGenre(String genre) {
        this.genre = new HibGenre(genre);
    }

    @Override
    public void setTitle(String title) {
        this.title = new HibTitle(title);
    }

    @Override
    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public void setPublisher(String publisher) {
        this.publisher = new HibPublisher(publisher);
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
    // TODO: What's the type of this method getRecBooks()
    @Override
    public List<Book> getRecBooks() {
        return null;
    }

    @Override
    public Dictionary<String, Double> getPrices() {
        return null;
    }
}
