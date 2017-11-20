package edu.swarthmore.cs.cs71.shelved.model.simple;

import edu.swarthmore.cs.cs71.shelved.model.api.RowShelf;
import edu.swarthmore.cs.cs71.shelved.model.api.ShelvedBook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class SimpleRowShelf implements RowShelf {
    private HashMap<SimpleShelvedBook, Integer> rowList = new HashMap<>();

    public SimpleRowShelf() {
    }

    public void addBook(SimpleShelvedBook shelvedBook, int position) {
        this.rowList.put(shelvedBook, position);
    }

    public void removeBook(SimpleShelvedBook book) {
        this.rowList.remove(book);
    }

    public int getBook(SimpleShelvedBook book) {
        return rowList.get(book);
    }


    public void resetPosition(SimpleShelvedBook book, int newPosition) {
       this.rowList.put(book, newPosition);
    }

    public Set<SimpleShelvedBook> getAllBooks() {
        return this.rowList.keySet();
    }
}