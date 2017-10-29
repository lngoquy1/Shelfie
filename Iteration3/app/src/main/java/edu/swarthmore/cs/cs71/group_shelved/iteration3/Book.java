package edu.swarthmore.cs.cs71.group_shelved.iteration3;

import java.util.Dictionary;
import java.util.List;

public interface Book {
    // Do we need to initialize these? How do we change the values? BookImpl already has these.
    Author author;
    Genre genre;
    Title title;
    int pages;
    Publisher publisher;

    // Returns a list of recommended books based on current book object.
    List<Book> getRecBooks();

    // Returns a dictionary of book source mapped to accompanying price.
    Dictionary<String, Double> getPrices();
}
