package edu.swarthmore.cs.cs71.shelved.shelved;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.shelved.shelvedModel.SearchViewModel;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class CameraFragment extends Fragment {
    static final int PICK_CONTACT_REQUEST = 1;  // The request code

    private TextView _ISBN;
    private TextView _Author;
    private TextView _Title;
    private static final String TAG = "CameraFragment";
//    final Continuation<SimpleBook> positiveContinuation = new Continuation<SimpleBook>() {
//        @Override
//        public void run(SimpleBook simpleBook) {
//            // TODO: modify aList, tell Adapter, callUpdateBook, Adapter of SimpleBook
//            ShelfFragment.books.add(simpleBook);
//            bookListAdapter.notifyDataSetChanged();
//            ShelfFragment.updateBook();
//        }
//    };

    private String getAddBookByScanUrl() {
        //AppCompatActivity act = new AppCompatActivity();
        return "http://" + getContext().getResources().getString((R.string.server_url)) + ":4567/searchByISBN";
    }

    public static CameraFragment newInstance() {
        CameraFragment fragment = new CameraFragment();
        Context context;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context;
        View rootView = inflater.inflate(R.layout.fragment_camera, container, false);
        context = rootView.getContext();
        _ISBN = (TextView) rootView.findViewById(R.id.ISBN);
        _Author = (TextView) rootView.findViewById(R.id.Author);
        _Title = (TextView) rootView.findViewById(R.id.Title);

        Intent intent = new Intent(context, ScannerActivity.class);

        Log.d(TAG, "about to call activity start");
        startActivityForResult(intent, PICK_CONTACT_REQUEST);

        return rootView;
        //return inflater.inflate(R.layout.fragment_camera, container, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PICK_CONTACT_REQUEST && data!=null) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                String ISBN = ScannerActivity.getISBN();
                _ISBN.setText(ISBN);
                Log.d(TAG, "past setting ISBN text");

                SearchViewModel searchViewModel = new SearchViewModel();

                AppSingleton.getInstance(getContext())
                        .setupSearchViewModel(getContext(),searchViewModel)
                        .addScan(ISBN);

                List<SimpleBook> searchResults = searchViewModel.getBooklist();
                SimpleBook book = searchResults.get(0);

                _Author.setText(book.getAuthor().getAuthorName());
                _Title.setText(book.getTitle().getTitle());

                Log.d(TAG, "past activity start");
            }
        }
    }
}
