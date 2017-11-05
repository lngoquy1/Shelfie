package edu.swarthmore.cs.cs71.shelved;

import java.util.ArrayList;
import java.util.List;

public class SimpleBookShelf implements BookShelf {
    private ArrayList<RowShelf> allRows;

    public SimpleBookShelf(int id) {
        this.allRows = new ArrayList<RowShelf>();
    }


    @Override
    public List<RowShelf> getAllRows() {
        return this.allRows;
    }

    public int getNumRows() {
        return this.allRows.size();
    }


    public void addRowShelf(int index, RowShelf rowShelf) {
        this.allRows.add(index, rowShelf);
    }


    public void removeRowShelf(RowShelf rowShelf) {
        this.allRows.remove(rowShelf);
    }
}
