package edu.swarthmore.cs.cs71.shelved.model.api;

import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleShelvedBook;

public interface CreatedList {

    // Add a book to a list.
    void addBook(SimpleShelvedBook shelvedBook);

    // Remove a book from a list.
    void removeBook(SimpleShelvedBook shelvedBook);

}
