package com.danshima.noodleapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class NoodleActivity extends AppCompatActivity {
    private Cursor cursor;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noodle);
        //set up toolbar as the normal app bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView noodleListPerCategory = findViewById(R.id.selected_noodleList);
        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
        try {
            database = databaseHelper.getReadableDatabase();
            cursor = database.query("NOODLE", new String[]{"_id", "NAME"}, null, null, null, null, null);
            //create the cursor adapter to fill the list view with values from the database
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{"NAME"},
                    new int[]{android.R.id.text2}, 0);
            noodleListPerCategory.setAdapter(listAdapter);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database error", Toast.LENGTH_SHORT);
            toast.show();
        }

        //show item detail using the listener when an item is clicked
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //starts DetailActivity
                Intent intent = new Intent(NoodleActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.CHOSEN_NOODLE_ITEM, (int) id);
                startActivity(intent);
            }
        };
        //connects the listener to the list view
        noodleListPerCategory.setOnItemClickListener(itemClickListener);
    }

    /**
     *This method is called when the database and cursor need to be closed.
     * They are closed when the cursor adapter doesn't need them anymore.
     * */
    @Override
         public void onDestroy(){
            super.onDestroy();
            cursor.close();
            database.close();
        }







    }

