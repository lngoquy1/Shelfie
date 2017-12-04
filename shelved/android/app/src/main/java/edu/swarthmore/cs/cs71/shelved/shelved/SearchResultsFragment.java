package edu.swarthmore.cs.cs71.shelved.shelved;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;

public class SearchResultsFragment extends Fragment {

    private ListView listView;
    private BookListAdapter bookListAdapter;

    public static SearchResultsFragment newInstance() {
        SearchResultsFragment fragment = new SearchResultsFragment();
        return fragment;
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

        this.bookListAdapter = new BookListAdapter(getContext(), ((SearchFragment)
                getActivity().getSupportFragmentManager().findFragmentById(R.id.search_results_container)).returnBooks());

        listView.setAdapter(bookListAdapter);

        return view;
    }

}
