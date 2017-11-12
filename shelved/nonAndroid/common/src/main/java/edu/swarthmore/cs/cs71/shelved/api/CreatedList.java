package edu.swarthmore.cs.cs71.shelved.api;

public interface CreatedList {

    // Add a book to a list.
    void addBook(SimpleShelvedBook shelvedBook);

    // Remove a book from a list.
    void removeBook(SimpleShelvedBook shelvedBook);

}
