package com.danshima.noodleapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.CheckBox;
import android.content.ContentValues;

public class DetailActivity extends MenuActivity {
    //the ID of the noodles that is used to get details of the noodle the user selected
    public static final String CHOSEN_NOODLE_ITEM = "noodleItem";
    private SQLiteOpenHelper databaseHelper;
    private SQLiteDatabase database;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //get a support Actionbar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        //enables the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        populateNoodleDetails();
    }

    /**
     * This method fetches data from the SQLite database and connects the data to the view.
     */
    private void populateNoodleDetails() {
        //get the noodle from the intent
        int noodleItem = (Integer) getIntent().getExtras().get(CHOSEN_NOODLE_ITEM);
        //create a cursor
        databaseHelper = new DatabaseHelper(this);
        try {
            database = databaseHelper.getReadableDatabase();
            cursor = database.query("NOODLE", new String[] {"NAME", "DESCRIPTION", "IMAGEID", "RESTAURANT", "FAVORITE"},
                    "_id = ?", new String[] {Integer.toString(noodleItem)}, null, null, null);

        } catch(SQLiteException e) {
            e.printStackTrace();
            Toast toastError = Toast.makeText(this, "Database error!!", Toast.LENGTH_SHORT);
            toastError.show();
        }
        //move to the first row in the Cursor
        if(cursor.moveToFirst()) {
            //get details from the cursor
            String name = cursor.getString(0);
            String description = cursor.getString(1);
            int image = cursor.getInt(2);
            String restaurant = cursor.getString(3);
            // 1 for checked, and 0 for unchecked in the favorite checkbox
            boolean isFavorite = (cursor.getInt(4) == 1);

            //populate the noodle name
            TextView nameNoodle = findViewById(R.id.name_info);
            //the value stored in the database becomes the name of the noodle
            nameNoodle.setText(name);

            //populate the noodle image
            ImageView imageNoodle = findViewById(R.id.image_info);
            imageNoodle.setImageResource(image);
            imageNoodle.setContentDescription(name);

            //populate the noodle description
            TextView descriptionNoodle = findViewById(R.id.description_info);
            //the description value in db becomes the description of the noodle
            descriptionNoodle.setText(description);

            //populate the restaurant info
            TextView restaurantNoodle = findViewById(R.id.restaurant_info);
            restaurantNoodle.setText(restaurant);

            //populate the favorite checkbox.
            CheckBox favoriteBtn = findViewById(R.id.add_to_favorite_btn);
            favoriteBtn.setChecked(isFavorite);
        }

    }

    /**
     * update the column favorite in the database when the checkbox is clicked
     * @param view The "save to favorite" CheckBox
     */
   public void saveFavorite(View view) {
        int noodleItem = (Integer) getIntent().getExtras().get(CHOSEN_NOODLE_ITEM);
        //Get the value of the checkbox
        CheckBox favorite = findViewById(R.id.add_to_favorite_btn);
        ContentValues noodleValues = new ContentValues();
        noodleValues.put("FAVORITE", favorite.isChecked());

        //Get a reference to the database and update the FAVORITE column
        databaseHelper = new DatabaseHelper(this);
        try{
            database = databaseHelper.getWritableDatabase();
            database.update("NOODLE", noodleValues, "_id = ?", new String[] {Integer.toString(noodleItem)});

        } catch(SQLiteException e) {
            Toast toastError = Toast.makeText(this, "Database error!!!", Toast.LENGTH_SHORT);
            toastError.show();
        }
    }

    /**
     * Need to override MenuActivity's onOptionsItemSelected method because the Up button doesn't work otherwise.
     * @param item The MenuItem object that is the action on the toolbar that was clicked by the user
     * @return true if clicked
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;
        }
        return true;
    }

}
