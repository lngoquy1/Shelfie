package edu.swarthmore.cs.cs71.shelved.simple;

import edu.swarthmore.cs.cs71.shelved.api.RowShelf;
import edu.swarthmore.cs.cs71.shelved.api.ShelvedBook;

import java.util.ArrayList;
import java.util.List;

public class SimpleRowShelf implements RowShelf {

    private ArrayList<ShelvedBook> rowList = new ArrayList<ShelvedBook>();



    public SimpleRowShelf() {
    }

    public void addBook(ShelvedBook shelvedBook, int position) {
        this.rowList.add(position, shelvedBook);
    }

    public void removeBook(int position) {
        this.rowList.remove(position);
    }

    public ShelvedBook getBook(int position) {
        return rowList.get(position);
    }


    public void resetPosition(int oldPosition, int newPosition) {
        ShelvedBook shelvedBook = getBook(oldPosition);
        removeBook(oldPosition);
        addBook(shelvedBook, newPosition);
    }


    public List<ShelvedBook> getAllBooks() {
        return this.rowList;
    }
}