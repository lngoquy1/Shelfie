package edu.swarthmore.cs.cs71.shelved.model.simple;

import edu.swarthmore.cs.cs71.shelved.model.api.RowShelf;
import edu.swarthmore.cs.cs71.shelved.model.api.ShelvedBook;

import java.util.ArrayList;
import java.util.List;

public class SimpleRowShelf implements RowShelf {
    private ArrayList<SimpleShelvedBook> rowList = new ArrayList<SimpleShelvedBook>();
    public String header = this.getClass().getSimpleName();

    public SimpleRowShelf() {
    }

    public void addBook(SimpleShelvedBook shelvedBook, int position) {
        this.rowList.add(position, shelvedBook);
    }

    public void removeBook(int position) {
        this.rowList.remove(position);
    }

    public SimpleShelvedBook getBook(int position) {
        return rowList.get(position);
    }

    // TODO: get position of book given book?

    public void resetPosition(int oldPosition, int newPosition) {
        SimpleShelvedBook shelvedBook = getBook(oldPosition);
        removeBook(oldPosition);
        addBook(shelvedBook, newPosition);
    }

    public List<SimpleShelvedBook> getAllBooks() {
        return this.rowList;
    }
}