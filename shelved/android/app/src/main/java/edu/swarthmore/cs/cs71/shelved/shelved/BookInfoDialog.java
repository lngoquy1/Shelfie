package edu.swarthmore.cs.cs71.shelved.shelved;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;

public class BookInfoDialog {
    public AlertDialog.Builder newInstance (Context context, SimpleBook book) {
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
