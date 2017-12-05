package edu.swarthmore.cs.cs71.shelved.shelved;

import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel {
    List<SimpleBook> books = new ArrayList<>();
    List<SearchViewModelListener> listeners = new ArrayList<>();

    // add, remove, clear books needs to call listeners
    // add listeners
}
