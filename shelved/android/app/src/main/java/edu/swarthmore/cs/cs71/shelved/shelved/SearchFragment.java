package edu.swarthmore.cs.cs71.shelved.shelved;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import edu.swarthmore.cs.cs71.shelved.model.bookData.BookInfo;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleBook;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;
import edu.swarthmore.cs.cs71.shelved.network.ValidSearchResponseISBN;
import edu.swarthmore.cs.cs71.shelved.network.serialization.GsonUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SearchFragment extends Fragment {

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
                if (CHOSEN == 0) {
                    Toast.makeText(getContext(), "Please choose a category", Toast.LENGTH_SHORT).show();
                } else {
                    BookInfo bookInfo = new BookInfo();
                    switch (CHOSEN) {
                        case ISBN:
//                            try {
////                                SimpleBook bookResult = null;
////                                bookResult.setAuthor(bookInfo.getAuthorFromISBN(s));
////                                bookResult.setTitle(bookInfo.getTitleFromISBN(s));
////                                books.add(bookResult);
//                                searchByISBN(s);
//                            } catch (IOException e){
//                                Toast.makeText(getContext(), "IOException", Toast.LENGTH_SHORT).show();
//                            } catch (XPathExpressionException e) {
//                                Toast.makeText(getContext(), "XPathExpressionException", Toast.LENGTH_SHORT).show();
//                            } catch (SAXException e) {
//                                Toast.makeText(getContext(), "SAXException", Toast.LENGTH_SHORT).show();
//                            } catch (ParserConfigurationException e) {
//                                Toast.makeText(getContext(), "ParserConfigurationException", Toast.LENGTH_SHORT).show();
//                            } catch (EmptyQueryException e) {
//                                Toast.makeText(getContext(), "EmptyQueryException", Toast.LENGTH_SHORT).show();
//                            } catch (NotFoundException e) {
//                                Toast.makeText(getContext(), "NotFoundException", Toast.LENGTH_SHORT).show();
//                            }
                            searchByISBN(s);
                        case TITLE:
                            // TODO
                        case AUTHOR:
                            //TODO
                    }
                }
                Log.d("SearchFragment", "submit text: " + CHOSEN);
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

    private String getSearchByISBN(){
        return "http://"+getContext().getResources().getString((R.string.server_url))+":4567/searchByISBN";
    }


    private void searchByISBN(final String ISBN) {
        final String TAG = "SearchByISBN";
        String cancel_req_tag = "SearchByISBN";
        StringRequest strReq = new StringRequest(Request.Method.POST, getSearchByISBN(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response){
                Log.d(TAG, "Search by response: " + response);

                ResponseMessage message = GsonUtils.makeMessageGson().fromJson(response, ResponseMessage.class);
                if (message.isResult()){
                    ValidSearchResponseISBN searchResponseISBN = (ValidSearchResponseISBN) message;
                }
                try {
                    Log.d(TAG, response);
                    JSONObject jObj = new JSONObject(response);
                    boolean error = !jObj.getBoolean("result");


                    if (!error) {
                        Log.d(TAG, "no error");
                        Toast.makeText(getContext(), "Results for ISBN "+ ISBN, Toast.LENGTH_SHORT).show();
                        Gson gson = new Gson();
                        SimpleBook book = gson.fromJson(jObj.getJSONObject("book").toString(), SimpleBook.class);


                        SearchViewModel searchViewModel = AppSingleton.getInstance(getContext()).getSearchViewModel(getContext());
                        searchViewModel.clearBooks();
                        searchViewModel.getBooklist().add(book);

                    } else {
                        Log.d(TAG, "error");
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("Error case");
                }

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Add book error: " + error.getMessage());
                Toast.makeText(getContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
//                        hideDialog(progressDialog);
            }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("isbn", ISBN);
                return params;
            }
        };
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
//
//
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
//
////     Instances of this class are fragments representing a single
////     object in our collection.
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
//
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
