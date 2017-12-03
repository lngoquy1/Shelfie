package edu.swarthmore.cs.cs71.shelved.shelved;


import android.Manifest;
import android.content.Intent;

import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.*;
import android.widget.*;

public class SearchFragment extends ListFragment {


    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

//    // This is the Adapter being used to display the list's data.
//    SimpleCursorAdapter mAdapter;
//
//    // If non-null, this is the current filter the user has provided.
//    String mCurFilter;
//
//    @Override public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        // Give some text to display if there is no data.  In a real
//        // application this would come from a resource.
//        setEmptyText("No phone numbers");
//
//        // We have a menu item to show in action bar.
//        setHasOptionsMenu(true);
//
//        // Create an empty adapter we will use to display the loaded data.
//        mAdapter = new SimpleCursorAdapter(getActivity(),
//                android.R.layout.simple_list_item_2, null,
//                new String[] { Contacts.DISPLAY_NAME, Contacts.CONTACT_STATUS },
//                new int[] { android.R.id.text1, android.R.id.text2 }, 0);
//        setListAdapter(mAdapter);
//
//        // Start out with a progress indicator.
//        setListShown(false);
//
//        // Prepare the loader.  Either re-connect with an existing one,
//        // or start a new one.
//        getLoaderManager().initLoader(0, null, this);
//    }
//
//    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        // Place an action bar item for searching.
//        MenuItem item = menu.add("Search");
//        item.setIcon(android.R.drawable.ic_menu_search);
//        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//        SearchView sv = new SearchView(getActivity());
//        sv.setOnQueryTextListener(this);
//        item.setActionView(sv);
//    }
//
//    public boolean onQueryTextChange(String newText) {
//        // Called when the action bar search text has changed.  Update
//        // the search filter, and restart the loader to do a new query
//        // with this filter.
//        mCurFilter = !TextUtils.isEmpty(newText) ? newText : null;
//        getLoaderManager().restartLoader(0, null, this);
//        return true;
//    }
//
//    @Override public boolean onQueryTextSubmit(String query) {
//        // Don't care about this.
//        return true;
//    }
//
//    @Override public void onListItemClick(ListView l, View v, int position, long id) {
//        // Insert desired behavior here.
//        Log.i("FragmentComplexList", "Item clicked: " + id);
//    }
//
//    // These are the Contacts rows that we will retrieve.
//    static final String[] CONTACTS_SUMMARY_PROJECTION = new String[] {
//            Contacts._ID,
//            Contacts.DISPLAY_NAME,
//            Contacts.CONTACT_STATUS,
//            Contacts.CONTACT_PRESENCE,
//            Contacts.PHOTO_ID,
//            Contacts.LOOKUP_KEY,
//    };
//
//    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//        // This is called when a new Loader needs to be created.  This
//        // sample only has one Loader, so we don't care about the ID.
//        // First, pick the base URI to use depending on whether we are
//        // currently filtering.
//        Uri baseUri;
//        if (mCurFilter != null) {
//            baseUri = Uri.withAppendedPath(Contacts.CONTENT_FILTER_URI,
//                    Uri.encode(mCurFilter));
//        } else {
//            baseUri = Contacts.CONTENT_URI;
//        }
//
//        // Now create and return a CursorLoader that will take care of
//        // creating a Cursor for the data being displayed.
//        String select = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND ("
//                + Contacts.HAS_PHONE_NUMBER + "=1) AND ("
//                + Contacts.DISPLAY_NAME + " != '' ))";
//        return new CursorLoader(getActivity(), baseUri,
//                CONTACTS_SUMMARY_PROJECTION, select, null,
//                Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC");
//    }
//
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//        // Swap the new cursor in.  (The framework will take care of closing the
//        // old cursor once we return.)
//        mAdapter.swapCursor(data);
//
//        // The list should now be shown.
//        if (isResumed()) {
//            setListShown(true);
//        } else {
//            setListShownNoAnimation(true);
//        }
//    }
//
//    public void onLoaderReset(Loader<Cursor> loader) {
//        // This is called when the last Cursor provided to onLoadFinished()
//        // above is about to be closed.  We need to make sure we are no
//        // longer using it.
//        mAdapter.swapCursor(null);
//    }

//    private Button searchButton;
//
//    public static SearchFragment newInstance() {
//        SearchFragment fragment = new SearchFragment();
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
//        //searchButton = (Button)rootView.findViewById(R.id.ex_search_button);
//        return rootView;
//    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getContext(), SearchableActivity.class);
//                startActivity(i);
//            }
//        });
//    }

//        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        menu.clear();
//        inflater.inflate(R.menu.search_menu, menu);
//        MenuItem item = menu.findItem(R.id.action_item2);
//        SearchView searchView = new SearchView(((MainActivity) getContext()).getSupportActionBar().getThemedContext());
//        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
//        MenuItemCompat.setActionView(item, searchView);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//        searchView.setOnClickListener(new View.OnClickListener() {
//                                          @Override
//                                          public void onClick(View v) {
//
//                                          }
//                                      }
//        );
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // handle item selection
//        switch (item.getItemId()) {
//            case R.id.search_bar:
//
//                //       onCall();   //your logic
//
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    @Override
//    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//        return null;
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//
//    }
//
//    @Override
//    public boolean onQueryTextSubmit(String s) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String s) {
//        return false;
//    }
}
