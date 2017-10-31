package edu.swarthmore.cs.cs71.group_shelved;

import java.util.ArrayList;

public class BookShelfImpl implements BookShelf {
    private ArrayList<RowShelf> allRows;

    public BookShelfImpl() {
        this.allRows = new ArrayList<>();
    }

    @Override
    public RowShelf search(ShelvedBook book) {

    }

    @Override
    public int getNumRows() {
        return this.allRows.size();
    }

    @Override
    public void addRowShelf(int index, RowShelf rowShelf) {
        this.allRows.add(index, rowShelf);
    }

    @Override
    public void removeRowShelf(RowShelf rowShelf) {
        this.allRows.remove(rowShelf);
    }
}
