package com.danshima.noodleapp;



import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * A subclass displayed in the main page that shows a list of noodle per category. 
 */
public class CategoryFragment extends Fragment {
    private Cursor cursor;
    private SQLiteDatabase database;
    //public static final String CHOSEN_CATEGORY_ITEM = "categoryItem";


    public CategoryFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_japanese, container, false);
        ListView noodleListPerCategory = view.findViewById(R.id.japanese_noodleList);


        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        //ListView noodleListPerCategory = (ListView) findViewById(R.id.japanese_noodleList);

        try {
            database = databaseHelper.getReadableDatabase();
            fetch(1);

            //create the cursor adapter to fill the list view with values from the database
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(getContext(), android.R.layout.simple_list_item_1, cursor, new String[]{"NAME"},
                    new int[]{android.R.id.text1}, 0);
            noodleListPerCategory.setAdapter(listAdapter);
        } catch (SQLiteException e) {
            //e.printStackTrace();
            Toast toast = Toast.makeText(getContext(), "Database is not working!", Toast.LENGTH_SHORT);
            toast.show();
        }

        //show item detail using the listener when an item is clicked
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> noodleListPerCategory, View view, int position, long id) {
                //starts DetailActivity
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.CHOSEN_NOODLE_ITEM, (int) id);
                startActivity(intent);
            }
        };
        //connects the listener to the list view
        noodleListPerCategory.setOnItemClickListener(itemClickListener);
        return view;
    }

    /**
     * This method is called when the database and cursor need to be closed.
     * They are closed when the cursor adapter doesn't need them anymore.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
        database.close();
    }

    public Cursor fetch(int categoryID) {
        int iID = 0;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            iID = bundle.getInt("IDItem", 3);
        }
        String selectQuery = "SELECT * FROM NOODLE WHERE CATEGORY=" + iID;
        //String selectQuery = "SELECT * FROM NOODLE WHERE CATEGORY=" + categoryID;
        cursor = database.rawQuery(selectQuery, null);

        //cursor = database.query("NOODLE", new String[]{"_id", "NAME"},
        // null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
















