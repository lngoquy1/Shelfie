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
//import edu.swarthmore.cs.cs71.shelved.network.ValidBookListUpdateResponse;
import edu.swarthmore.cs.cs71.shelved.network.serialization.GsonUtils;
import edu.swarthmore.cs.cs71.shelved.sandbox.Main;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

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
    private SimpleBook book;
    private String userID;

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

    public static ShelfFragment newInstance(String userID) {
        ShelfFragment fragment = new ShelfFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putString("userID", userID);
        fragment.setArguments(args);
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
        Bundle args = getArguments();
        userID = args.getString("userID", "");
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and show AddBookDialog
                AddBookDialog alert = new AddBookDialog(getContext(), userID, new Continuation<SimpleBook>() {
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
                book = (SimpleBook)adapterView.getItemAtPosition(position); // String object of cover, title, and author values for book object
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

                        if (message.isResult()){
                            //ValidBookListUpdateResponse bookListUpdateResponse = (ValidBookListUpdateResponse) message;
                        }
                        try {
                            Log.d(TAG, response);
                            JSONObject jObj = new JSONObject(response);
                            boolean error = !jObj.getBoolean("result");
                            if (!error) {
                                Log.d(TAG, "no error");
                                // TODO: turn Response into a list of book, update the list, tell Adapter
                                // populate the array
                                JSONArray jArr = jObj.getJSONArray("bookList");


                                ShelfFragment.this.books.clear();
                                for (int i=0; i<jArr.length();i++){
                                    Gson gson = new Gson();
                                    SimpleBook book = gson.fromJson(jArr.getJSONObject(i).toString(), SimpleBook.class);
                                    Log.d(TAG, "book: "+book.getTitle().getTitle());
                                    Log.d(TAG, book.getTitle().getTitle());
//                                    if (!ShelfFragment.this.books.contains(book)) {
//                                        ShelfFragment.this.books.add(book);
//                                    }
                                    ShelfFragment.this.books.add(book);

                                }
                                bookListAdapter.notifyDataSetChanged();
                                Log.d(TAG, "Should be updating books");


                            } else {
                                Log.d(TAG, "error");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("Error case");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Update book list error: "+ error.getMessage() + "caused by:" + error.getCause());
            }
        });
        AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, cancel_req_tag);

    }
    private AlertDialog.Builder bookInfoDialog () {
        Context context = getContext();
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Book");

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        // getting info from book object
        String bookTitle = book.getTitle().getTitle();
        String bookAuthor = book.getAuthor().getAuthorName();
        //String bookGenre = book.getGenre().getGenre();
        //int bookPages = book.getPages();
        //String bookPublisher = book.getPublisher().getPublisher();

        // create text views for each item
        final TextView titleBox = new TextView(context);
        //titleBox.setText(book);
        final TextView authorBox = new TextView(context);
        //final TextView genreBox = new TextView(context);
        //final TextView pagesBox = new TextView(context);
        //final TextView publisherBox = new TextView(context);

        // set text views to book info
        titleBox.setText(bookTitle);
        authorBox.setText(bookAuthor);

        layout.addView(titleBox);
        layout.addView(authorBox);

//        final TextView authorBox = new TextView(context);
//        authorBox.setText("author");
//        layout.addView(authorBox);

        alert.setView(layout);

        return alert;
    }

}
