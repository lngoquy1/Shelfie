package edu.swarthmore.cs.cs71.shelved.shelved;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import org.w3c.dom.Text;

public class BookInfoFragment extends Fragment {
    //private static SimpleBook book;
    private String title;
    private String author;
    private String genre;
    private String publisher;
    private String imageUrl;
    private int pages;

    public static BookInfoFragment newInstance(SimpleBook simpleBook) {
        Log.d("AUTHOR IN INFO FRAG", simpleBook.getAuthor().getAuthorName());
        Bundle bundle = new Bundle();
        bundle.putString("author", simpleBook.getAuthor().getAuthorName());
        bundle.putString("title", simpleBook.getTitle().getTitle());
        //bundle.putString("genre", simpleBook.getGenre().getGenre());
        //bundle.putString("image_url", simpleBook.getImageUrl());
        //bundle.putString("publisher", simpleBook.getPublisher().getPublisher());
        //bundle.putInt("pages", simpleBook.getPages());

        BookInfoFragment fragment = new BookInfoFragment();
        fragment.setArguments(bundle);

        return fragment;
    }


    public void setArguments(Bundle bundle) {
        this.author = bundle.getString("author");
        this.title = bundle.getString("title");
        this.genre = bundle.getString("genre");
        this.publisher = bundle.getString("publisher");
        this.imageUrl = bundle.getString("image_url");
        this.pages = bundle.getInt("pages");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_book_info, container, false);
        //Log.d("BOOK INFO", book.getAuthor().getAuthorName());
        setFieldsFromBook(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

//    public void setFieldsFromBook(SimpleBook book, View view) {
    public void setFieldsFromBook(View view) {
        TextView titleText = (TextView) view.findViewById(R.id.book_title);
        //title.setText(book.getTitle().getTitle());
        titleText.setText(this.title);

        TextView authorText = (TextView) view.findViewById(R.id.book_author);
//        author.setText(book.getAuthor().getAuthorName());
        authorText.setText(this.author);

        TextView genreText = (TextView) view.findViewById(R.id.book_genre);
//        genre.setText(book.getGenre().getGenre());
        //genreText.setText(this.genre);

        TextView publisherText = (TextView) view.findViewById(R.id.book_publisher);
//        publisher.setText(book.getPublisher().getPublisher());
        //publisherText.setText(this.publisher);

        TextView pagesText = (TextView) view.findViewById(R.id.book_pages);
//        pages.setText(book.getPages());
        //pagesText.setText(this.pages);
    }
}
