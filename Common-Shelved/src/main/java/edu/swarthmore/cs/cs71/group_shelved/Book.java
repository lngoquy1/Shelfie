package edu.swarthmore.cs.cs71.group_shelved;

import java.util.Dictionary;
import java.util.List;

public interface Book {

    // Returns a list of recommended books based on current book object.
    List<Book> getRecBooks();

    // Returns a dictionary of book source mapped to accompanying price.
    Dictionary<String, Double> getPrices();
}
