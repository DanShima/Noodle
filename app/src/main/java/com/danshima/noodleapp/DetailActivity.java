package com.danshima.noodleapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.CheckBox;
import android.content.ContentValues;

public class DetailActivity extends AppCompatActivity {
    public static final String CHOSEN_NOODLE_ITEM = "noodleItem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get the noodle from the intent
        int noodleItem = (Integer) getIntent().getExtras().get(CHOSEN_NOODLE_ITEM);

        //create a cursor
        DatabaseHelper databaseHandler = new DatabaseHelper(this);
        try {
            SQLiteDatabase database = databaseHandler.getReadableDatabase();
            Cursor cursor = database.query("NOODLE", new String[] {"NAME", "DESCRIPTION", "IMAGEID", "RESTAURANT", "FAVORITE"},
                    "_id = ?", new String[] {Integer.toString(noodleItem)}, null, null, null);
            //move to the first row in the Cursor
            if(cursor.moveToFirst()) {
                //get details from the cursor
                String name = cursor.getString(0);
                String description = cursor.getString(1);
                int image = cursor.getInt(2);
                String restaurant = cursor.getString(3);
                //if the favorite column has a value of 1, it means true
                boolean isFavorite = (cursor.getInt(4) == 1);
                //populate the noodle name
                TextView nameNoodle = findViewById(R.id.name);
                //the value stored in the database becomes the name of the noodle
                nameNoodle.setText(name);
                //populate the noodle description
                TextView descriptionNoodle = findViewById(R.id.description);
                //the description value in db becomes the description of the noodle
                descriptionNoodle.setText(description);
                //populate the noodle image
                ImageView imageNoodle = findViewById(R.id.image);
                imageNoodle.setImageResource(image);
                imageNoodle.setContentDescription(name);
                //populate the restaurant info
                TextView restaurantNoodle = findViewById(R.id.restaurant_info);
                restaurantNoodle.setText(restaurant);
                //populate the favorite checkbox
                CheckBox favorite = findViewById(R.id.favorite_checkbox);
                favorite.setChecked(isFavorite);
            }
            cursor.close();
            database.close();
        } catch(SQLiteException e) {
            //shows an error message to the user when db is not working
            Toast toastError = Toast.makeText(this, "Database error!!", Toast.LENGTH_SHORT);
            toastError.show();
        }
    }
    //update the database when the checkbox is clicked
    public void saveFavorite(View view) {
        int noodleItem = (Integer) getIntent().getExtras().get(CHOSEN_NOODLE_ITEM);
        //Get the value of the checkbox
        CheckBox favorite = findViewById(R.id.favorite_checkbox);
        ContentValues noodleValues = new ContentValues();
        noodleValues.put("FAVORITE", favorite.isChecked());


        //Get a reference to the database and update the FAVORITE column
        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
        try{
            SQLiteDatabase database = databaseHelper.getWritableDatabase();
            database.update("NOODLE", noodleValues, "_id = ?", new String[] {Integer.toString(noodleItem)});
            database.close();
        } catch(SQLiteException e) {
            Toast toastError = Toast.makeText(this, "Database error!!!", Toast.LENGTH_SHORT);
            toastError.show();
        }
    }










}
