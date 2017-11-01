package edu.swarthmore.cs.cs71.group_shelved.common;

import java.util.List;

public interface AllShelves {
    void addBookShelf();
    void removeBookShelf(BookShelf bookShelf);
    List<ShelvedBook> sort(SortType sortType);
    BookShelf search(ShelvedBook shelvedBook);
}
