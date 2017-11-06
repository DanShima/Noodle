package com.danshima.noodleapp;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class JapaneseFragment extends Fragment {
    private DatabaseHelper databaseHandler;
    private SQLiteDatabase database;
    private List<Noodle> myNoodleList;
    private ListDataAdapter mDataAdapter;
    private Cursor cursor;
    View listView;
    Context context;

    public static JapaneseFragment newInstance() {
        return new JapaneseFragment();
    }


    public JapaneseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_japanese, container, false);

    }
}
