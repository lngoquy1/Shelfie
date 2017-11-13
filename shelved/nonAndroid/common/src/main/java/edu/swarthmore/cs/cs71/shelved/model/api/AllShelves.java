package edu.swarthmore.cs.cs71.shelved.model.api;

import edu.swarthmore.cs.cs71.shelved.model.simple.SortType;

import java.util.List;

public interface AllShelves {
    void addBookShelf();
    void removeBookShelf(BookShelf bookShelf);
    List<ShelvedBook> sort(SortType sortType);
    BookShelf search(ShelvedBook shelvedBook);
}
