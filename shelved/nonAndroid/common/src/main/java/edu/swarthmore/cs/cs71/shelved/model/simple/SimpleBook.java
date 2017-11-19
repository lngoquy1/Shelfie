package edu.swarthmore.cs.cs71.shelved.model.simple;

import edu.swarthmore.cs.cs71.shelved.model.api.Book;

import java.util.Dictionary;
import java.util.List;

public class SimpleBook implements Book {

    private SimpleAuthor author;
    private SimpleGenre genre;
    private SimpleTitle title;
    private int pages;
    private SimplePublisher publisher;
    public String header = this.getClass().getSimpleName();

    public SimpleBook(){

    }


    public void setAuthor(String author) {
        this.author = new SimpleAuthor(author);
    }

    public void setGenre(String genre) {
        this.genre = new SimpleGenre(genre);
    }

    public void setTitle(String title) {
        this.title = new SimpleTitle(title);
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setPublisher(String publisher) {
        this.publisher = new SimplePublisher(publisher);
    }

    public SimpleAuthor getAuthor() {
        return author;
    }

    public SimpleGenre getGenre() {
        return genre;
    }

    public SimpleTitle getTitle() {
        return title;
    }

    public int getPages() {
        return pages;
    }

    public SimplePublisher getPublisher() {
        return publisher;
    }



    public List<Book> getRecBooks() {
        //query Amazon's API
        //http://docs.aws.amazon.com/AWSECommerceService/latest/DG/SuggestingSimilarItemstoBuy.html
        return null;
    }


    public Dictionary<String, Double> getPrices() {
        //query Amazon's API (and more)
        //http://docs.aws.amazon.com/AWSECommerceService/latest/DG/EX_RetrievingPriceInformation.html
        return null;
    }
}
