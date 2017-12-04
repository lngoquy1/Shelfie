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

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search_results, container, false);

        super.onCreate(savedInstanceState);

        listView = (ListView) view.findViewById(android.R.id.list);

        return view;
    }

}
