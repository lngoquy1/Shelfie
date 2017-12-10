package edu.swarthmore.cs.cs71.shelved.shelved;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsFragment extends Fragment {

    // takes in same view model as SearchFragment

    private ListView listView;
    private SearchListAdapter searchListAdapter;
    private List<SimpleBook> books = new ArrayList<>();

    public static SearchResultsFragment newInstance() {
        SearchResultsFragment fragment = new SearchResultsFragment();
        return fragment;
    }

    public SearchListAdapter getSearchListAdapter() {
        return searchListAdapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search_results, container, false);

        super.onCreate(savedInstanceState);

        listView = (ListView) view.findViewById(android.R.id.list);

        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                SimpleBook book = (SimpleBook)adapterView.getItemAtPosition(position); // String object of cover, title, and author values for book object
                BookInfoDialog dialog = new BookInfoDialog();
                AlertDialog.Builder alert = dialog.newInstance(getContext(), book);
                alert.show();
            }
        });



        books = AppSingleton.getInstance(getContext()).getSearchViewModel(getContext()).getBooklist();
        this.searchListAdapter = new SearchListAdapter(getContext(), books);

        listView.setAdapter(searchListAdapter);

        SearchViewModel searchViewModel = AppSingleton.getInstance(getContext()).getSearchViewModel(getContext());
        searchViewModel.addSearchViewModelListener(new SearchViewModelListener() {
            @Override
            public void searchResultsChanged() {
                searchListAdapter.notifyDataSetChanged();
            }
        });
    }

}
