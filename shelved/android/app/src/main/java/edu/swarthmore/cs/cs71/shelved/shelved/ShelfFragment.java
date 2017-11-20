package edu.swarthmore.cs.cs71.shelved.shelved;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import butterknife.ButterKnife;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ShelfFragment extends ListFragment {

    private static final int BOOKS_AMOUNT = 2;

    private SimpleBook[] books = new SimpleBook[BOOKS_AMOUNT];

    private String[] titles = new String[BOOKS_AMOUNT];
    private int[] covers = {
            R.mipmap.logo
    };
    private String[] authors = new String[BOOKS_AMOUNT];

    private ListView bookList;
    private ImageButton addBook;


    public void initializeBooks(SimpleBook[] books) {

        // Manual creation of SimpleBook objects - will later populate from database
        for (int j = 0; j < books.length; j++) {
            books[j] = new SimpleBook();
        }

        // Manually setting book fields for now
        books[0].setTitle("Kafka by the Shore");
        books[0].setAuthor("Haruki Murakami");

        books[1].setTitle("Harry Potter and the Sorcerer's Stone");
        books[1].setAuthor("J.K. Rowling");

        // Add book fields to separate String arrays to populate the list adapter
        for (int i = 0; i < books.length; i++) {
            titles[i] = books[i].getTitle().getTitle();
            authors[i] = books[i].getAuthor().getAuthorName();
        }
    }

    public static ShelfFragment newInstance() {
        ShelfFragment fragment = new ShelfFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        initializeBooks(books);
        // Sets view to fragment_shelf
        View view = inflater.inflate(R.layout.fragment_shelf, container, false);

        // Sets the ListView item in fragment shelf to our custom list item, bookList
        bookList = (ListView)view.findViewById(android.R.id.list);
        SimpleAdapter adapter = adaptBookList();
        bookList.setAdapter(adapter);

        addBook = (ImageButton)view.findViewById(R.id.add_book);

        return view;
    }

    private SimpleAdapter adaptBookList() {
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<books.length;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("title", titles[i]);
            hm.put("author", authors[i]);
            hm.put("cover", Integer.toString(covers[0]) );
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = {"title","author","cover"};

        // Ids of views in listview_layout
        int[] to = {R.id.title_book, R.id.author_book, R.id.cover};

        // Instantiating an adapter to store each items
        // R.layout.book_list_item defines the layout of each item
        return new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.book_list_item, from, to);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and show AddBookDialog
                AddBookDialog alert = new AddBookDialog(getContext());
                AddBookDialog alert1 = alert.newInstance();
                alert1.show();
            }
        });
        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder alert = bookInfoDialog();
                alert.show();
            }
        });
    }

    private AlertDialog.Builder bookInfoDialog () {
        Context context = getContext();
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Book");

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        final TextView titleBox = new TextView(context);
        titleBox.setText("Title");
        layout.addView(titleBox);

        final TextView authorBox = new TextView(context);
        authorBox.setText("Author");
        layout.addView(authorBox);

        alert.setView(layout);

        return alert;
    }

}
