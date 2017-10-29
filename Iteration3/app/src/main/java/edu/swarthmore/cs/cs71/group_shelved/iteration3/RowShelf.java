package edu.swarthmore.cs.cs71.group_shelved.iteration3;

import java.util.ArrayList;

public interface RowShelf {
    ArrayList<ShelvedBook> rowList = new ArrayList<>();
    void addBook(ShelvedBook shelvedBook, int position);
    void removeBook(ShelvedBook shelvedBook);
    void resetPosition(ShelvedBook shelvedBook, int newPosition);
}

