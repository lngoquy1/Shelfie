package edu.swarthmore.cs.cs71.shelved.shelved;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;
import edu.swarthmore.cs.cs71.shelved.network.serialization.GsonUtils;
import edu.swarthmore.cs.cs71.shelved.shelved.shelvedModel.ShelfUpdatedListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

//import edu.swarthmore.cs.cs71.shelved.network.ValidBookListUpdateResponse;




public class ShelfFragment extends ListFragment {
    private BookListAdapter bookListAdapter;

    private static final String TAG = "ShelfFragment";
//    private static int BOOKS_AMOUNT = 2;

    private ListView bookList;
    private ImageButton addBook;

    // In order to populate the individual book view
    private SimpleBook book;
//    private String userID;

    public static ShelfFragment newInstance(String userID) {
        ShelfFragment fragment = new ShelfFragment();
        // Supply index input as an argument.
//        Bundle args = new Bundle();
//        args.putString("userID", userID);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // Sets view to fragment_shelf
        View view = inflater.inflate(R.layout.fragment_shelf, container, false);

        // Sets the ListView item in fragment shelf to our custom list item, bookList
        bookList = (ListView)view.findViewById(android.R.id.list);
        this.bookListAdapter = new BookListAdapter(getContext(), AppSingleton.getInstance(getContext()).getModel(getContext()).getBookList());
        bookList.setAdapter(bookListAdapter);

        addBook = (ImageButton)view.findViewById(R.id.add_book);
        Bundle args = getArguments();


        // notifies and tells GUI to redraw shelf when book list changes
        AppSingleton.getInstance(getContext()).getModel(getContext()).addShelfUpdatedListener(new ShelfUpdatedListener() {
            @Override
            public void shelfUpdated() {
                bookListAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout_main, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and show AddBookDialog
                AddBookDialog alert = new AddBookDialog(getContext());
                Log.d(TAG, "show add book dialog");
                Log.d(TAG, "called newInstance");
                alert.show();
            }
        });
        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                book = (SimpleBook)adapterView.getItemAtPosition(position);
                //setFieldsFromBook(book, view);
                Log.d("AUTHOR IN SHELF FRAG", book.getAuthor().getAuthorName());
                Fragment fragment = BookInfoFragment.newInstance(book);
                replaceFragment(fragment);
            }
        });
    }

    public void setFieldsFromBook(SimpleBook book, View view) {
        TextView title = (TextView) view.findViewById(R.id.book_title);
        title.setText(book.getTitle().getTitle());

        TextView author = (TextView) view.findViewById(R.id.book_author);
        author.setText(book.getAuthor().getAuthorName());

        TextView genre = (TextView) view.findViewById(R.id.book_genre);
        genre.setText(book.getGenre().getGenre());

        TextView publisher = (TextView) view.findViewById(R.id.book_publisher);
        publisher.setText(book.getPublisher().getPublisher());

        TextView pages = (TextView) view.findViewById(R.id.book_pages);
        pages.setText(book.getPages());
    }



}
