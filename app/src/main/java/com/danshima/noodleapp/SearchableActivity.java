package com.danshima.noodleapp;


import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class SearchableActivity extends MenuActivity {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private Cursor cursor;
    private ListView resultList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        resultList = findViewById(R.id.search_result_list);
        handleIntent(getIntent());
    }

    /**
    * This method delivers the intent when the activity is invoked again.
    * when the user executes a search from this activity, we don't create
    * a new instance of this activity, because of launchMode="singleTop".=
    * */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    /**
     * This method decides what to do with the search request
     * @param intent The search request made by the user
     */
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

    /**
     * This method searches in the database to match the input given by the user.
     * @param input The search word or description input from the user
     * @return Cursor the cursor for finding the relevant information
     */

    public Cursor searchInDatabase(String input) {
        databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getReadableDatabase();
        String []columns = {"_id", "NAME", "DESCRIPTION"};
        String selectionString = "NAME" + " LIKE '%" + input + "%' OR " + "DESCRIPTION" + " LIKE '%" + input + "%'";
        //sort the filtered list in alphabetical order
        String orderBy = "NAME" + " ASC";
        cursor = database.query("NOODLE", columns, selectionString, null, null, null, orderBy);
        if (cursor != null) {cursor.moveToFirst();}
        return cursor;
    }


    /**
     * This method displays results for the given query in the designated list view.
     * @param query The search query
     */
    private void showSearchResult(String query) {
            searchInDatabase(query);
        if (cursor == null) {
            Toast toast = Toast.makeText(this, "No result found", Toast.LENGTH_SHORT);
            toast.show();
        } else {
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
                    newIntent.putExtra(DetailActivity.CHOSEN_NOODLE_ITEM, (int) id);
                    startActivity(newIntent);
                }
            });
        }
    }
}










