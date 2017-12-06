package edu.swarthmore.cs.cs71.shelved.shelved;

import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel {
    private List<SimpleBook> books = new ArrayList<>();
    private List<SearchViewModelListener> searchViewModelListeners = new ArrayList<>();

    public void addBook(SimpleBook book) {
        this.books.add(book);
    }

    public List<SimpleBook> getBooklist() {
        return books;
    }

    public void clearBooks() {
        this.books.clear();
    }

    public void addSearchViewModelListener (SearchViewModelListener listener) {
        this.searchViewModelListeners.add(listener);
    }

    public void notifySearchViewModelListeners() {
        for (SearchViewModelListener listener : this.searchViewModelListeners) {
            listener.searchResultsChanged();
        }
    }





    // add, remove, clear books needs to call listeners
    // add listeners
}
