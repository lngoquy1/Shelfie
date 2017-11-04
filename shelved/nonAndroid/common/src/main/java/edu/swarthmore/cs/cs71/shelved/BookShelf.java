package edu.swarthmore.cs.cs71.shelved;

import java.util.List;

public interface BookShelf {
    List<RowShelf> getAllRows();
    int getNumRows();
    void addRowShelf(int index, RowShelf rowShelf);
    void removeRowShelf(RowShelf rowShelf);
}
