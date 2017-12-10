package edu.swarthmore.cs.cs71.shelved.shelved;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import org.w3c.dom.Text;

public class BookInfoFragment extends Fragment {
    private static SimpleBook book;

    public static BookInfoFragment newInstance(SimpleBook book) {
        BookInfoFragment fragment = new BookInfoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_book_info, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



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
