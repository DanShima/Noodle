package com.danshima.noodleapp;

import android.widget.ArrayAdapter;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Testing some method in LogActivity
 */

public class UserLogTest {
    @Test
    public void removeLogTest() {

        ArrayList<String> experiences = new ArrayList<>();
        experiences.add("Hello");
        experiences.add("Test");
        experiences.add("Ship it");
        String log = experiences.get(2);
        experiences.remove(log);
        assertEquals("Remove the log", 2, experiences.size());
        assertEquals("Log removed", "Ship it", log);
    }
}
