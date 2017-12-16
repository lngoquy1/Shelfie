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
import com.koushikdutta.ion.Ion;
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
    private ImageView cover;
    private ImageButton addBook;

    public static BookInfoFragment newInstance(SimpleBook simpleBook) {
        Log.d("AUTHOR IN INFO FRAG", simpleBook.getAuthor().getAuthorName());
        Bundle bundle = new Bundle();
        bundle.putString("author", simpleBook.getAuthor().getAuthorName());
        bundle.putString("title", simpleBook.getTitle().getTitle());
        bundle.putString("genre", simpleBook.getGenre().getGenre());
        bundle.putString("image_url", simpleBook.getImageUrl());
        bundle.putString("publisher", simpleBook.getPublisher().getPublisher());
        bundle.putInt("pages", simpleBook.getPages());

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
        addBook = (ImageButton)rootView.findViewById(R.id.add_book);
        setFieldsFromBook(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and show AddBookDialog
                //AddBookDialog alert = new AddBookDialog(getContext());
                Log.d("Book info fragment", "show add book dialog");
                Log.d("Book info fragment", "called newInstance");
                //alert.show();
            }
        });
    }

//    public void setFieldsFromBook(SimpleBook book, View view) {
    public void setFieldsFromBook(View view) {
        ImageView coverImage = (ImageView) view.findViewById(R.id.book_cover);
        Ion.with(coverImage).placeholder(R.mipmap.logo).error(R.mipmap.logo).load(imageUrl);

        TextView titleText = (TextView) view.findViewById(R.id.book_title);
        //title.setText(book.getTitle().getTitle());
        titleText.setText(this.title);

        TextView authorText = (TextView) view.findViewById(R.id.book_author);
//        author.setText(book.getAuthor().getAuthorName());
        authorText.setText(this.author);

        TextView genreText = (TextView) view.findViewById(R.id.book_genre);
//        genre.setText(book.getGenre().getGenre());
        String genreDisplayText = "Genre: " + this.genre;
        genreText.setText(genreDisplayText);

        TextView publisherText = (TextView) view.findViewById(R.id.book_publisher);
//        publisher.setText(book.getPublisher().getPublisher());
        String pubDisplayText = "Publisher: " + this.publisher;
        publisherText.setText(pubDisplayText);

        TextView pagesText = (TextView) view.findViewById(R.id.book_pages);
//        pages.setText(book.getPages());
        String pageDisplayText = "Pages: " + Integer.toString(this.pages);
        pagesText.setText(pageDisplayText);
    }
}
