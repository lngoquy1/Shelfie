package edu.swarthmore.cs.cs71.shelved.model.simple;

import edu.swarthmore.cs.cs71.shelved.model.api.BookShelf;
import edu.swarthmore.cs.cs71.shelved.model.api.RowShelf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleBookShelf implements BookShelf {
    private ArrayList<SimpleRowShelf> allRows;

    public SimpleBookShelf() {
    }
    @Override
    public void configureBookShelf(int numRows){
        this.allRows = new ArrayList<>();
        for (int i=0;i<numRows;i++){
            this.allRows.add(new SimpleRowShelf());
        }
    }

    public List<SimpleRowShelf> getAllRows() {
        return this.allRows;
    }

    @Override
    public int getNumRows() {
        return this.allRows.size();
    }

    public SimpleRowShelf getRowShelf(int rowPosition){
        return this.allRows.get(rowPosition);
    }
    public void addRowShelf(int index, SimpleRowShelf rowShelf) {
        this.allRows.add(index, rowShelf);
    }

    public void removeRowShelf(SimpleRowShelf rowShelf) {
        this.allRows.remove(rowShelf);
    }
}
