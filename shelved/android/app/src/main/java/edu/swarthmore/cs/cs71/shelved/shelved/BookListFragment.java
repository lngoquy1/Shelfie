package edu.swarthmore.cs.cs71.shelved.shelved;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import edu.swarthmore.cs.cs71.shelved.model.simple.ReadingList;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gridview;
    private ImageButton addList;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lists, container, false);

        super.onCreate(savedInstanceState);

        gridview = (GridView) view.findViewById(R.id.list_gridview);
        gridview.setAdapter(new IconAdapter(getContext()));
        gridview.setOnItemClickListener(this);

        addList = (ImageButton) view.findViewById(R.id.add_list);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = addListDialog();
                alert.show();
                // Handle what happens when a list is created
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(getContext(), position, Toast.LENGTH_SHORT).show();
    }

    private class IconAdapter extends BaseAdapter {
        private Context mContext;

        public IconAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

        // references to our images
        private Integer[] mThumbIds = {
                R.mipmap.logo,
                R.mipmap.logo,
                R.mipmap.logo,
                R.mipmap.logo
        };
    }

    private AlertDialog.Builder addListDialog() {
        Context context = getContext();
        AddListDialog alert = new AddListDialog(context);
        alert.setTitle("Add List");


        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText titleBox = new EditText(context);
        titleBox.setHint("List Name");
        layout.addView(titleBox);

        final Switch status = new Switch(context);
        status.setText("Public?");
        status.setShowText(true);
        status.setTextOn("Yes");
        status.setTextOff("No");
        layout.addView(status);

        alert.setView(layout);

        alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Log.v("title", titleBox.getText().toString());

                String titleString = titleBox.getText().toString();

        addList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and show AddBookDialog
                AddBookDialog alert = new AddBookDialog(getContext());

            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        return alert;
    }
}
