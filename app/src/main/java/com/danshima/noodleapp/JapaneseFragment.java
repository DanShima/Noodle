package com.danshima.noodleapp;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class JapaneseFragment extends Fragment {



    public JapaneseFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_noodle, container, false);
        ListView listView = view.findViewById(R.id.selected_noodleList);


        return view;

    }


}
