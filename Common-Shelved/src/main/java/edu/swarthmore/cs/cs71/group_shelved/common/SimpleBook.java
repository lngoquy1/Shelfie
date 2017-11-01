package edu.swarthmore.cs.cs71.group_shelved.common;

import java.util.Dictionary;
import java.util.List;

public class SimpleBook implements Book {
    Author author;
    Genre genre;
    Title title;
    int pages;
    Publisher publisher;



    public SimpleBook(String author, String genre, String title, int pages, String publisher) {
        this.author = new Author(author);
        this.genre = new Genre(genre);
        this.title = new Title(title);
        this.pages = pages;
        this.publisher = new Publisher(publisher);
    }
    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public Title getTitle() {
        return title;
    }

    public int getPages() {
        return pages;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    @Override
    public List<Book> getRecBooks() {
        //query Amazon's API
        //http://docs.aws.amazon.com/AWSECommerceService/latest/DG/SuggestingSimilarItemstoBuy.html
        return null;
    }

    @Override
    public Dictionary<String, Double> getPrices() {
        //query Amazon's API (and more)
        //http://docs.aws.amazon.com/AWSECommerceService/latest/DG/EX_RetrievingPriceInformation.html
        return null;
    }
}
