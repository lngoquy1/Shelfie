package edu.swarthmore.cs.cs71.shelved.shelved;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleReadingList;
import edu.swarthmore.cs.cs71.shelved.shelved.shelvedModel.ListsUpdatedListener;

import java.util.ArrayList;
import java.util.List;

//import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleReadingList;

public class BookListFragment extends Fragment {
    private GridView gridview;
    private ImageButton addList;
    private List<SimpleReadingList> readingLists;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lists, container, false);

        super.onCreate(savedInstanceState);

        gridview = (GridView) view.findViewById(R.id.list_gridview);
        addList = (ImageButton) view.findViewById(R.id.add_list);

        //gridview.setAdapter(new IconAdapter(getContext()));
        readingLists = AppSingleton.getInstance(getContext()).getModel(getContext()).getLists();

        AppSingleton.getInstance(getContext()).getModel(getContext()).addListsUpdatedListener(new ListsUpdatedListener() {
            @Override
            public void listsUpdated() {
//                readingListAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AddListDialog alert = new AddListDialog(getContext(), null);
                alert.show();
                // Handle what happens when a list is created
            }
        });
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), String.valueOf(i), Toast.LENGTH_SHORT).show();
            }
        });
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

    private final int ROW_ITEMS = 3;
    private final class GridAdapter extends BaseAdapter {

        final List<SimpleReadingList> mItems;
        final int mCount;

        private GridAdapter(final ArrayList<String> items) {

            mCount = items.size() * ROW_ITEMS;
            mItems = readingLists;
        }

        @Override
        public int getCount() {
            return mCount;
        }

        @Override
        public Object getItem(int i) {
            return mItems.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int position, final View convertView, final ViewGroup parent) {

            View view = convertView;

            if (view == null) {
                view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            }

            final TextView text = (TextView) view.findViewById(android.R.id.text1);

            text.setText(mItems.get(position).getName());

            return view;
        }
    }
}
