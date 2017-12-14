package edu.swarthmore.cs.cs71.shelved.shelved;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.koushikdutta.ion.Ion;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;

import java.util.List;

public class BookListAdapter extends ArrayAdapter<SimpleBook> {

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
        Ion.with(cover).error(R.mipmap.logo).load(book.getImageUrl());
        return convertView;
    }
}