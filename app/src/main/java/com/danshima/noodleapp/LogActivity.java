package com.danshima.noodleapp;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * This activity class allows user to quickly note down their experience or a noodle dish they have tried.
 *
 */


public class LogActivity extends MenuActivity {
    private ArrayAdapter<String> adapter;
    private ArrayList<String> experiences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        //set up toolbar as the normal app bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("My List");
        setSupportActionBar(toolbar);

        experiences = new ArrayList<>();

        final EditText enterLog = findViewById(R.id.enterLog);
        //when user enters something into the editText view
        enterLog.setOnKeyListener(new View.OnKeyListener() {
                                      //click actions add the contents of the editText widgets to the arrayList
                                      public boolean onKey(View v, int keyCode, KeyEvent event) {
                                          if (event.getAction() == KeyEvent.ACTION_DOWN)
                                              //and he presses enter
                                              if (keyCode == KeyEvent.KEYCODE_ENTER) {
                                                  String input = enterLog.getText().toString();
                                                  //if he didn't enter any words, then nothing happens
                                                  if (input.length() == 0) {
                                                      return true;
                                                  }
                                                  Noodle newNoodle = new Noodle(input);
                                                  input = newNoodle.toString();
                                                  experiences.add(0, input);
                                                  enterLog.setText("");
                                                  return true;
                                              }
                                          return false;
                                      }
                                  });

                ListView listView = findViewById(R.id.newLog);
                //create an array adapter that connects the array to the list view
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, experiences);
                //now the adapter is connected to the list view
                listView.setAdapter(adapter);

    }


    public void addLog(String log) {
        Noodle newNoodle = new Noodle(log);
        log = newNoodle.toString();
        experiences.add(log);
        adapter.notifyDataSetChanged();

    }

}
