package edu.swarthmore.cs.cs71.shelved.shelved;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ShelfFragment extends ListFragment implements OnItemClickListener {

    public static ShelfFragment newInstance() {
        ShelfFragment fragment = new ShelfFragment();
        return fragment;
    }

    // Array of strings storing country names
    String[] books = new String[] {
            "Kafka by the Shore",
            "Harry Potter"
    };

    // Array of integers points to images stored in /res/drawable/
    int[] imgs = new int[]{
            R.drawable.shelved_logo,
            R.drawable.shelved_logo
    };

    // Array of strings to store currencies
    String[] author = new String[]{
            "Haruki Murakami",
            "J.K. Rowling"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // Each row in the list stores country name, author and flag
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<books.length;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", "Book : " + books[i]);
            hm.put("cur","Author : " + author[i]);
            hm.put("flag", Integer.toString(imgs[i]) );
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "flag","txt","cur" };

        // Ids of views in listview_layout
        int[] to = { R.id.flag,R.id.txt,R.id.cur};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.book_list_item, from, to);

        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
