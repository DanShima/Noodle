package com.danshima.noodleapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * This activity class allows user to quickly note down their experience or a noodle dish they have tried.
 * */


public class LogActivity extends MenuActivity {
    private UserLog log;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> experiences;
    private static final String SHARED_PREFS_NAME = "MY_SHARED_PREF";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        //set up toolbar as the normal app bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("My List");
        setSupportActionBar(toolbar);

        //get references to layout widgets
        Button button = findViewById(R.id.add_to_log);
        final ListView listView = findViewById(R.id.newLog);

       experiences = new ArrayList<>();
       experiences = getArray();
        //create an array adapter that connects the array to the list view
       adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, experiences);

        //enable listener so user input is registered when the button is clicked
       Button.OnClickListener listener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText enterLog = findViewById(R.id.enterLog);
                String input = enterLog.getText().toString();
                //if the user doesn't enter any text, a toast will be shown
               if (input.length() == 0) {
                   Toast toastError = Toast.makeText(LogActivity.this, "Write something!", Toast.LENGTH_SHORT);
                   toastError.show();
               } else {
                try{
                    //add the new experience to the arrayList
                    addLog(input);
                    adapter.notifyDataSetChanged();
                    enterLog.setText("");
                }catch(Exception e){
                    e.printStackTrace();
                    Toast toastError = Toast.makeText(LogActivity.this, "Cannot be added", Toast.LENGTH_SHORT);
                    toastError.show();
                }
            }}
        };
        button.setOnClickListener(listener);
        listView.setAdapter(adapter);
        listView.setDividerHeight(3);

        //if the user clicks an item in the list view long enough, the item is removed
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long arg) {
                removeLog(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    /**
     * This method stores the data in the arrayList "experiences" through Shared Preferences,
     * as it otherwise disappears when you close the app or go to another activity.
     * @return true if stored in an editor object
     */
   public boolean saveArray() {
        //enter key and mode(the file can only be accessed using calling app)
        SharedPreferences sp = this.getSharedPreferences(SHARED_PREFS_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Set<String> set = new HashSet<>();
        set.addAll(experiences);
        editor.putStringSet("list", set);
        return editor.commit();
    }

    /**
     * This method retrieves the data stored through SharedPreferences.
     * @return the stored ArrayList
     */
   public ArrayList<String> getArray() {
        SharedPreferences sp = this.getSharedPreferences(SHARED_PREFS_NAME, Activity.MODE_PRIVATE);
        //if shared preference is null, the method return empty Hashset and not null
        Set<String> set = sp.getStringSet("list", new HashSet<String>());
        return new ArrayList<>(set).sort(Comparator.<String>naturalOrder());
    }

    /**
     * When the activity stops, saveArray() is called to do its work so no data is lost
     */
    public void onDestroy() {
        saveArray();
        super.onDestroy();
    }

    /**
     * This method adds a new experience or log (user input) to the ArrayList
     * @param newExp the experience/text entered
     */
   public void addLog(String newExp) {
        //convert the UserLog object into string for the arrayList
        log = new UserLog(newExp);
        newExp = log.toString();
        experiences.add(0, newExp);
    }

    /**
     * This method removes an entry from the log list if the user long clicks it.
     *  @param position the index of the entry
     *  */
   public void removeLog(int position) {
        String log = experiences.get(position);
        experiences.remove(log);
    }

}
