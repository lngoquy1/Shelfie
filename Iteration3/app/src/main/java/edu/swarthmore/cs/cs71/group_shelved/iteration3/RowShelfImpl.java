package edu.swarthmore.cs.cs71.group_shelved.iteration3;

import java.util.ArrayList;

public class RowShelfImpl implements RowShelf {
    ArrayList<ShelvedBook> rowList = new ArrayList<>();
    @Override
    public void addBook(ShelvedBook shelvedBook, int position) {
        rowList.add(position, shelvedBook);
    }

    @Override
    public void removeBook(ShelvedBook shelvedBook) {
        rowList.remove(shelvedBook);
    }

    @Override
    public void resetPosition(ShelvedBook shelvedBook, int position) {
        rowList.remove(shelvedBook);
        rowList.add(position, shelvedBook);
    }
}