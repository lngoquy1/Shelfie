package edu.swarthmore.cs.cs71.group_shelved;

import java.util.ArrayList;

public class RowShelfImpl implements RowShelf {
    ArrayList<ShelvedBook> rowList = new ArrayList<>();
    @Override
    public void addBook(ShelvedBook shelvedBook, int position) {
        rowList.add(position, shelvedBook);
    }

    @Override
    public void removeBook(int position) {
        rowList.remove(position);
    }

    @Override
    public ShelvedBook getBook(int position){
        return rowList.get(position);
    }
    @Override
    public void resetPosition(int oldPosition, int newPosition) {
        ShelvedBook shelvedBook = getBook(oldPosition);
        removeBook(oldPosition);
        addBook(shelvedBook, newPosition);
    }
}