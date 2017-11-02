package edu.swarthmore.cs.cs71.shelved;

import java.util.ArrayList;

public class SimpleRowShelf implements RowShelf {

    private ArrayList<ShelvedBook> rowList = new ArrayList<>();
    private int id;


    public SimpleRowShelf(ArrayList<ShelvedBook> rowList, int id) {
        this.rowList = rowList;
        this.id = id;
    }

    @Override
    public void addBook(ShelvedBook shelvedBook, int position) {
        this.rowList.add(position, shelvedBook);
    }

    @Override
    public void removeBook(int position) {
        this.rowList.remove(position);
    }

    @Override
    public ShelvedBook getBook(int position) {
        return rowList.get(position);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void resetPosition(int oldPosition, int newPosition) {
        ShelvedBook shelvedBook = getBook(oldPosition);
        removeBook(oldPosition);
        addBook(shelvedBook, newPosition);
    }

}