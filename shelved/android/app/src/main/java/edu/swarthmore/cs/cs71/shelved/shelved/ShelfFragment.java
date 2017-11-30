package edu.swarthmore.cs.cs71.shelved.shelved;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;
import edu.swarthmore.cs.cs71.shelved.network.serialization.GsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class ShelfFragment extends ListFragment {
    private BookListAdapter bookListAdapter;

    private static final String TAG = "ShelfFragment";

//    private static int BOOKS_AMOUNT = 2;

    private List<SimpleBook> books = new ArrayList<>();

    private List<String> titles = new ArrayList<>();
    private List<Integer> covers = new ArrayList<>();
    private List<String> authors = new ArrayList<>();

    private ListView bookList;
    private ImageButton addBook;

    // In order to populate the individual book view
    private String book;

//    private ArrayAdapter<SimpleBook> arrayAdapter = new ArrayAdapter<SimpleBook>(this, R.layout.book_list_item, books);



    public void initializeBooks(List<SimpleBook> books) {

//        // Manual creation of SimpleBook objects - will later populate from database
//        for (int j = 0; j < books.length; j++) {
//            books[j] = new SimpleBook();
//        }
        SimpleBook book1 = new SimpleBook();
        book1.setTitle("Kafka by the Shore");
        book1.setAuthor("Haruki Murakami");
        SimpleBook book2 = new SimpleBook();
        book2.setTitle("Harry Potter and the Sorcerer's Stone");
        book2.setAuthor("J.K. Rowling");




        // Add book fields to separate String arrays to populate the list adapter
        for (int i = 0; i < books.size(); i++) {
            titles.add(books.get(i).getTitle().getTitle());
            authors.add(books.get(i).getAuthor().getAuthorName());
            covers.add(R.mipmap.logo);
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
        this.bookListAdapter = new BookListAdapter(getContext(), books);
        bookList.setAdapter(bookListAdapter);
        addBook = (ImageButton)view.findViewById(R.id.add_book);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and show AddBookDialog
                AddBookDialog alert = new AddBookDialog(getContext(), new Continuation<SimpleBook>() {
                    @Override
                    public void run(SimpleBook simpleBook) {
                        // TODO: modify aList, tell Adapter, callUpdateBook, Adapter of SimpleBook
                        ShelfFragment.this.books.add(simpleBook);
                        bookListAdapter.notifyDataSetChanged();
                        updateBook();
                    }
                });
                Log.d(TAG, "show add book dialog");
                Log.d(TAG, "called newInstance");
                alert.show();
            }
        });
        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                book = adapterView.getItemAtPosition(position).toString(); // String object of cover, title, and author values for book object
                AlertDialog.Builder alert = bookInfoDialog();
                alert.show();
            }
        });
    }
    private String getUpdateBookListUrl(){
        return "http://"+getActivity().getApplicationContext().getResources().getString((R.string.server_url))+":4567/updateBook";
    }

    public void updateBook(){
        final String TAG = "UpdateBook";
        String cancel_req_tag = "updateBook";
        StringRequest strReq = new StringRequest(Request.Method.POST, getUpdateBookListUrl(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Update book response: " + response);
                        ResponseMessage message = GsonUtils.makeMessageGson().fromJson(response, ResponseMessage.class);
                        // TODO: turn Response into a list of book, update the list, tell Adapter
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
        titleBox.setText(book);
        layout.addView(titleBox);

//        final TextView authorBox = new TextView(context);
//        authorBox.setText("author");
//        layout.addView(authorBox);

        alert.setView(layout);

        return alert;
    }
    private static class BookListAdapter extends ArrayAdapter<SimpleBook> {

        public BookListAdapter(Context context, List<SimpleBook> bookList) {
            super(context, R.layout.book_list_item, bookList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            if (convertView == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                convertView = vi.inflate(R.layout.book_list_item, null);
            }
            SimpleBook book = getItem(position);
            TextView title = (TextView) convertView.findViewById(R.id.title_book);
            TextView author = (TextView) convertView.findViewById(R.id.author_book);
            ImageView cover = (ImageView) convertView.findViewById(R.id.cover);
            title.setText(book.getTitle().getTitle());
            author.setText(book.getAuthor().getAuthorName());
            cover.setImageResource(R.mipmap.logo);
            return convertView;
        }
    }

}
