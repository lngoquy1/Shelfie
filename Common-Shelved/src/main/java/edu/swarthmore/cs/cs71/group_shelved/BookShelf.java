package edu.swarthmore.cs.cs71.group_shelved;

public interface BookShelf {
    RowShelf search(ShelvedBook book);
    int getNumRows();
    void addRowShelf(int index, RowShelf rowShelf);
    void removeRowShelf(RowShelf rowShelf);
}
