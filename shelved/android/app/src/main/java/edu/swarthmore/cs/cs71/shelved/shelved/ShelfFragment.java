package edu.swarthmore.cs.cs71.shelved.shelved;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import edu.swarthmore.cs.cs71.shelved.model.api.Book;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ShelfFragment extends ListFragment implements OnItemClickListener {

    private static final int BOOKS_AMOUNT = 2;

    private SimpleBook[] books = new SimpleBook[BOOKS_AMOUNT];

    private String[] titles = new String[BOOKS_AMOUNT];
    private int[] covers = {
            R.mipmap.logo
    };
    private String[] authors = new String[BOOKS_AMOUNT];


    public void initializeBooks(SimpleBook[] books) {

        // Manual creation of SimpleBook objects - will later populate from database
        books[0] = new SimpleBook();
        books[1] = new SimpleBook();

        // manually setting book fields for now
        books[0].setTitle("Kafka by the Shore");
        books[0].setAuthor("Haruki Murakami");

        books[1].setTitle("Harry Potter and the Sorcerer's Stone");
        books[1].setAuthor("J.K. Rowling");

        for (int i = 0; i < books.length; i++) {
            titles[i] = books[i].getTitle().getTitle();
            authors[i] = books[i].getAuthor().getAuthorName();
        }
    }

//    String[] titles = new String[] {
//            "Kafka by the Shore",
//            "Harry Potter"
//    };
//
//    // Array of integers points to images stored in /res/drawable/
//    int[] covers = new int[]{
//            R.mipmap.logo,
//            R.mipmap.logo
//    };
//
//    // Array of strings to store currencies
//    String[] authors = new String[]{
//            "Haruki Murakami",
//            "J.K. Rowling"
//    };

    public static ShelfFragment newInstance() {
        ShelfFragment fragment = new ShelfFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        initializeBooks(books);

        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<books.length;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("title", titles[i]);
            hm.put("author", authors[i]);
            hm.put("cover", Integer.toString(covers[0]) );
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "title","author","cover" };

        // Ids of views in listview_layout
        int[] to = {R.id.title,R.id.author,R.id.cover};

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
