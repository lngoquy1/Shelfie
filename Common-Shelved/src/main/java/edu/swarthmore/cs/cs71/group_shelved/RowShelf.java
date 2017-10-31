package edu.swarthmore.cs.cs71.group_shelved;

public interface RowShelf {
    void addBook(ShelvedBook shelvedBook, int position);
    void removeBook(ShelvedBook shelvedBook);
    void resetPosition(ShelvedBook shelvedBook, int newPosition);
}

