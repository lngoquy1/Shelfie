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
import android.support.v4.app.*;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.*;
import android.widget.*;

public class SearchFragment extends Fragment{

    // Started on creating the different views for the 3 types of searches
    // Will probably start with first one search working

    private SearchView searchView;
    public static final int ISBN = 1;
    public static final int TITLE = 2;
    public static final int AUTHOR = 3;
    int CHOSEN = 0;

    private Button ISBNBtn;
    private Button titleBtn;
    private Button authorBtn;

    PagerAdapter mPagerAdapter;
    ViewPager mViewPager;


    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);


        ISBNBtn = (Button) view.findViewById(R.id.ISBN_button);
        titleBtn = (Button) view.findViewById(R.id.Title_button);
        authorBtn = (Button) view.findViewById(R.id.Author_button);

        searchView = (SearchView) view.findViewById(R.id.search_view);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("Search books");

//        mPagerAdapter = new PagerAdapter(getFragmentManager());
//
//        mViewPager = (ViewPager) view.findViewById(R.id.pager);
//        mViewPager.setAdapter(mPagerAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ISBNBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CHOSEN = ISBN;
                Fragment fragment = SearchResultsFragment.newInstance();
                replaceFragment(fragment);
            }
        });

        titleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CHOSEN = TITLE;
                Fragment fragment = SearchResultsFragment.newInstance();
                replaceFragment(fragment);
            }
        });

        authorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CHOSEN = AUTHOR;
                Fragment fragment = SearchResultsFragment.newInstance();
                replaceFragment(fragment);
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (ISBN == 0) {
                    Toast.makeText(getContext(), "Please choose a category", Toast.LENGTH_SHORT).show();
                } else {
                    return true;
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d("SearchFragment", "change text");
                return false;
            }
        });
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.search_results_view, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


//    @Override
//    public void onClick(View view) {
//        Fragment fragment = null;
//        switch (view.getId()) {
//            case R.id.ISBN_button:
//                CHOSEN = ISBN;
//                Log.d("Search click", "ISBN");
//                break;
//            case R.id.Title_button:
//                CHOSEN = TITLE;
//                Log.d("Search click", "Title");
//                break;
//            case R.id.Author_button:
//                CHOSEN = AUTHOR;
//                Log.d("Search click", "Author");
//                break;
//        }
//        fragment = SearchResultsFragment.newInstance();
//        replaceFragment(fragment);
//    }


//    // Since this is an object collection, use a FragmentStatePagerAdapter,
//    // and NOT a FragmentPagerAdapter.
//    private class PagerAdapter extends FragmentStatePagerAdapter {
//
//        public PagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            Fragment fragment = new ObjectFragment();
//            Bundle args = new Bundle();
//
//            //Example object is an integer
//            args.putInt(ObjectFragment.ARG_OBJECT, position+1);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        @Override
//        public int getCount() {
//            return 3;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return "OBJECT " + (position + 1);
//        }
//    }

    // Instances of this class are fragments representing a single
    // object in our collection.
//    public static class ObjectFragment extends Fragment {
//        public static final String ARG_OBJECT = "object";
//
//        @Override
//        public View onCreateView(LayoutInflater inflater,
//                                 ViewGroup container, Bundle savedInstanceState) {
//            // The last two arguments ensure LayoutParams are inflated
//            // properly.
//
//
//            //TODO Create layout xml for the category fragments (Search by ISBN, Title, Author)
//            //https://developer.android.com/training/implementing-navigation/lateral.html
//
////            View rootView = inflater.inflate(
////                    R.layout.fragment_collection_object, container, false);
////            Bundle args = getArguments();
////            ((TextView) rootView.findViewById(android.R.id.text1)).setText(
////                    Integer.toString(args.getInt(ARG_OBJECT)));
////            return rootView;
//
//            View view = null;
//            return view;
//        }
//
//    }

}
