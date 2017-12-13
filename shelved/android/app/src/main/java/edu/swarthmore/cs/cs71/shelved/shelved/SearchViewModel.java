package edu.swarthmore.cs.cs71.shelved.shelved;

import android.util.Log;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.shelved.SearchViewModelListener;
import edu.swarthmore.cs.cs71.shelved.shelved.shelvedModel.ScanAddedListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchViewModel {
    private List<SimpleBook> books = new ArrayList<>();
    private List<SearchViewModelListener> searchViewModelListeners = new ArrayList<>();
    Set<ScanAddedListener> scanAddedListeners = new HashSet<ScanAddedListener>();

    public void addBook(SimpleBook book) {
        this.books.add(book);
        notifySearchViewModelListeners();
    }

    public List<SimpleBook> getBooklist() {
        return books;
    }

    public void clearBooks() {
        this.books.clear();
        notifySearchViewModelListeners();
    }

    public void addSearchViewModelListener (SearchViewModelListener listener) {
        this.searchViewModelListeners.add(listener);
    }

    public void notifySearchViewModelListeners() {
        for (SearchViewModelListener listener : this.searchViewModelListeners) {
            listener.searchResultsChanged();
        }
    }


    //////////// SCANNER STUFF ? :( //////////////

    public void addScan(String ISBN) {
        Log.d("ADDSCAN", "IN ADD SCAN");
        notifyScanAddedListeners(ISBN);
    }

    public void addScanListener(ScanAddedListener newScanAddedListener) {
        Log.d("ADDSCANLISTENER", "IN ADD SCAN LISTENER");

        scanAddedListeners.add(newScanAddedListener);
    }

    public void notifyScanAddedListeners(String ISBN) {
        Log.d("NOTIFY LISTENERS SCAN", "IN NOTIFY ADD SCAN LISTENERS");
        for (ScanAddedListener listener:this.scanAddedListeners) {
            listener.scanAdded(ISBN);
        }
    }




    // add, remove, clear books needs to call listeners
    // add listeners
}
