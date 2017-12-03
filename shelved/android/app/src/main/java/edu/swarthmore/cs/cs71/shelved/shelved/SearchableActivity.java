package edu.swarthmore.cs.cs71.shelved.shelved;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

//TODO Create progress dialog (spinning wheel) while search query is being done

public class SearchableActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
        }
    }

}