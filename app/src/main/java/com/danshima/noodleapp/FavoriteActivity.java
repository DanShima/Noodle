package com.danshima.noodleapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class FavoriteActivity extends AppCompatActivity {
    private Cursor favoritesCursor;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //get a support Actionbar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        //enables the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        showFavoriteList();
        populateFavoriteList();

    }

    private void showFavoriteList() {
        //add an OnItemClickListener
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //if the noodle option in the list view is clicked, start DetailActivity

                    Intent intent = new Intent(FavoriteActivity.this, DetailActivity.class);
                    startActivity(intent);

            }
        };
        //add the listener to the list view
        ListView listview = findViewById(R.id.favorite_noodleList);
        listview.setOnItemClickListener(itemClickListener);
    }

    //populate the favorite_noodleList list view with user's favorite noodles.
    private void populateFavoriteList() {
        ListView favoriteList = findViewById(R.id.favorite_noodleList);
        //get reference to the database
        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getReadableDatabase();
        //create a cursor that gets the values of the _id and NAME columns when FAVORITE = 1
        favoritesCursor = database.query("NOODLE", new String[] {"_id", "NAME"}, "FAVORITE = 1",
        null, null, null, null);
        //create a new cursor adapter
        CursorAdapter favoriteAdapter = new SimpleCursorAdapter(FavoriteActivity.this,
                android.R.layout.simple_list_item_1, favoritesCursor, new String[] {"NAME"}, new int[] {android.R.id.text1}, 0);
        //set the cursor adapter to the list view
        favoriteList.setAdapter(favoriteAdapter);

        //navigate to DetailActivity if a noodle is clicked
        favoriteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FavoriteActivity.this, DetailActivity.class);
                //need to include the ID of the noodle specifically!
                intent.putExtra(DetailActivity.CHOSEN_NOODLE_ITEM, (int) id);
                startActivity(intent);
            }
        });
    }


    //this method is called when user goes back to FavoriteActivity after adding favorites.
    //we need a new cursor because new added favorites are not shown while the current cursor is still active
    @Override
    public void onRestart() {
        super.onRestart();
        //new favorites are not shown in the list. create a new cursor
       try {
           Cursor newCursor = database.query("NOODLE", new String[]{"_id", "NAME"}, "FAVORITE = 1",
                   null, null, null, null);
           //get reference to the list view's cursor adapter
           ListView listFavorites = findViewById(R.id.favorite_noodleList);
           CursorAdapter adapter = (CursorAdapter) listFavorites.getAdapter();
           //change the current cursor to the new cursor
           adapter.changeCursor(newCursor);
           favoritesCursor = newCursor;
       } catch(SQLiteException e) {
           e.printStackTrace();
       }

    }


    //Close the cursor and database afterward
    @Override
    public void onDestroy(){
        super.onDestroy();
        favoritesCursor.close();
        database.close();
    }





}
