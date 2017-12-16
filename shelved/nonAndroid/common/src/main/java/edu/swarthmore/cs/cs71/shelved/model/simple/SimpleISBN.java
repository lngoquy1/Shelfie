package edu.swarthmore.cs.cs71.shelved.model.simple;

import edu.swarthmore.cs.cs71.shelved.model.api.ISBN;

public class SimpleISBN implements ISBN {
    private String isbn;
    public String header = this.getClass().getSimpleName();

    public SimpleISBN(String isbn) {
        this.isbn = isbn;
    }

    public String getISBN() {
        return isbn;
    }
}
