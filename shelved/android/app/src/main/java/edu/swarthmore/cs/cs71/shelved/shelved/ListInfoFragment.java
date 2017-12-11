package edu.swarthmore.cs.cs71.shelved.shelved;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;

public class ListInfoFragment extends ListFragment {
    private BookListAdapter bookListAdapter;
    private static final String TAG = "ListInfoFragment";
    private ListView bookList;
    private ImageButton addBook;
    private SimpleBook book;

    public static ListInfoFragment newInstance(String userID) {
        ListInfoFragment fragment = new ListInfoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_list, container, false);

        bookList = (ListView)view.findViewById(android.R.id.list);
        //this.bookListAdapter = new BookListAdapter(getContext(), AppSingleton.getInstance(getContext()))
        //booklist.setAdapter(bookListAdapter);

        addBook = (ImageButton)view.findViewById(R.id.add_book);
        Bundle argss = getArguments();

        // notifies and tells GUI to redraw shelf when book list changes
        // TODO: chagne this to use updatelist stuff
//        AppSingleton.getInstance(getContext()).getModel(getContext()).addShelfUpdatedListener(new ShelfUpdatedListener() {
//            @Override
//            public void shelfUpdated() {
//                bookListAdapter.notifyDataSetChanged();
//            }
//        });

        return view;
    }

    public void replaceFragment(android.support.v4.app.Fragment someFragment) {
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

        //TODO: change tthis stuff once we have list stuff in shelf Model
//        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                book = (SimpleBook)adapterView.getItemAtPosition(position);
//                //setFieldsFromBook(book, view);
//                Log.d("AUTHOR IN SHELF FRAG", book.getAuthor().getAuthorName());
//                android.support.v4.app.Fragment fragment = BookInfoFragment.newInstance(book);
//                replaceFragment(fragment);
//            }
//        });
    }


}
