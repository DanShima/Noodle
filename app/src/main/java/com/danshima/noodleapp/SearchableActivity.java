package com.danshima.noodleapp;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.app.SearchManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchableActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private Cursor cursor;
    ListView resultList;
    private static String QUERY_EXTRA_KEY = "QUERY_EXTRA_KEY";
    private SimpleCursorAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        resultList = findViewById(R.id.search_result_list);
        // Create a new adapter and bind it to the List View
       // adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{"NAME"},
                //new int[]{android.R.id.text1}, 0);
       // resultList.setAdapter(adapter);

        handleIntent(getIntent());
    }

    // Because this activity has set launchMode="singleTop", the system calls this method
    // to deliver the intent if this activity is currently the foreground activity when
    // invoked again (when the user executes a search from this activity, we don't create
    // a new instance of this activity, so the system delivers the search intent here)
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        //if the activity was called to process a search request, do something with the search word/query
        try {
            intent = getIntent();
            if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
                //handles the search
                String searchWord = intent.getStringExtra(SearchManager.QUERY);

                showSearchResult(searchWord);

            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(this, "error there!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    public Cursor searchInDatabase(String input) {
        databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getReadableDatabase();
        String []columns = {"_id", "NAME", "DESCRIPTION"};
        String selectionString = "NAME" + " LIKE '%" + input + "%' OR " + "DESCRIPTION" + " LIKE '%" + input + "%'";
        cursor = database.query("NOODLE", columns, selectionString, null, null, null, null);
        if (cursor != null) {cursor.moveToFirst();}
        return cursor;
    }







    /**
     * Searches the dictionary and displays results for the given query.
     *
     * @param query The search query
     */
    private void showSearchResult(String query) {
        searchInDatabase(query);

            resultList = findViewById(R.id.search_result_list);
            //create a new cursor adapter
            CursorAdapter adapter = new SimpleCursorAdapter(SearchableActivity.this,
                    android.R.layout.simple_list_item_1, cursor, new String[]{"NAME"}, new int[]{android.R.id.text1}, 0);
            //set the cursor adapter to the list view
            resultList.setAdapter(adapter);

            // Define the on-click listener for the list items
            resultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Build the Intent used to open WordActivity with a specific word Uri
                    Intent newIntent = new Intent(SearchableActivity.this, DetailActivity.class);
                    startActivity(newIntent);
                }
            });



    }
}



        /*

        Cursor cursor = database.query("NOODLE", new String[] {"NAME", "DESCRIPTION", "RESTAURANT"},
                "_id = ?", new String[] {query}, null, null,null);

        if (cursor == null) {
            Toast toast = Toast.makeText(this, "No result found", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            //create a new cursor adapter
            CursorAdapter adapter = new SimpleCursorAdapter(SearchableActivity.this,
                    android.R.layout.simple_list_item_1, cursor, new String[] {"NAME"}, new int[] {android.R.id.text1}, 0);
            //set the cursor adapter to the list view
           resultList.setAdapter(adapter);

            // Define the on-click listener for the list items
            resultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Build the Intent used to open WordActivity with a specific word Uri
                    Intent newIntent = new Intent(SearchableActivity.this, DetailActivity.class);
                    startActivity(newIntent);
                }
            });
        }
    }*/







