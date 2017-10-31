package edu.swarthmore.cs.cs71.group_shelved;

import java.util.Dictionary;
import java.util.List;

public interface Book {
    // Do we need to initialize these? How do we change the values? edu.swarthmore.cs.cs71.group_shelved.BookImpl already has these.
//    edu.swarthmore.cs.cs71.group_shelved.Author author;
//    edu.swarthmore.cs.cs71.group_shelved.Genre genre;
//    edu.swarthmore.cs.cs71.group_shelved.Title title;
//    int pages;
//    edu.swarthmore.cs.cs71.group_shelved.Publisher publisher;

    // Returns a list of recommended books based on current book object.
    List<Book> getRecBooks();

    // Returns a dictionary of book source mapped to accompanying price.
    Dictionary<String, Double> getPrices();
}
