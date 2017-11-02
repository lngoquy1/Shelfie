package edu.swarthmore.cs.cs71.shelved;

import java.util.ArrayList;
import java.util.List;

public class SimpleBookShelf implements BookShelf {
    private ArrayList<RowShelf> allRows;

    public SimpleBookShelf() {
        this.allRows = new ArrayList<>();
    }

    @Override
    public List<RowShelf> getAllRows() {
        return this.allRows;
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
