package edu.swarthmore.cs.cs71.shelved.shelved;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import edu.swarthmore.cs.cs71.shelved.model.simple.ReadingList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookListFragment extends ListFragment {

    private static final int LISTS_AMOUNT = 2;

    private ReadingList[] bookList = new ReadingList[LISTS_AMOUNT];

    private ListView booksList;
    private ImageButton addList;

    public String[] listNames = new String[LISTS_AMOUNT];
    public boolean[] listStatus = new boolean[LISTS_AMOUNT];

    private int[] covers = {
            R.mipmap.logo
    };

    // In order to populate the individual list view
    private String list;

    public static BookListFragment newInstance() {
        BookListFragment fragment = new BookListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Sets view to fragment_shelf
        View view = inflater.inflate(R.layout.fragment_lists, container, false);

        // Sets the ListView item in fragment shelf to our custom list item, bookList
        booksList = (ListView)view.findViewById(android.R.id.list);
        SimpleAdapter adapter = adaptBookList();
        booksList.setAdapter(adapter);

        addList = (ImageButton)view.findViewById(R.id.add_list);

        return view;
    }

    private SimpleAdapter adaptBookList() {
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<bookList.length;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("Name", listNames[i]);
            hm.put("Status", String.valueOf(listStatus[i]));
            hm.put("Picture", Integer.toString(covers[0]) );
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = {"Name","Status","Picture"};

        // Ids of views in listview_layout
        int[] to = {R.id.title_book, R.id.author_book, R.id.cover};

        // Instantiating an adapter to store each items
        // R.layout.book_list_item defines the layout of each item
        return new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.book_list_item, from, to);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and show AddBookDialog
                AddBookDialog alert = new AddBookDialog(getContext());
                
            }
        });
        booksList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                list = adapterView.getItemAtPosition(position).toString(); // String object of name, status, and picture values for list object
//                AlertDialog.Builder alert = bookInfoDialog();
//                alert.show();
            }
        });
    }

}
