package edu.swarthmore.cs.cs71.shelved;

import java.util.Dictionary;
import java.util.List;

public interface Book {
    // TODO: Need a method to signify that a book has been lent out
    // TODO: Should a book know it location in row
    // Getters
    void setAuthor(String author);
    void setGenre(String genre);
    void setTitle(String title);
    void setPages(int pages);
    void setPublisher(String publisher);
    Author getAuthor();
    Genre getGenre();
    Title getTitle();
    int getPages();
    Publisher getPublisher();


    // Returns a list of recommended books based on current book object.
    List<Book> getRecBooks();

    // Returns a dictionary of book source mapped to accompanying price.
    // Dont use a string here
    Dictionary<String, Double> getPrices();
}
