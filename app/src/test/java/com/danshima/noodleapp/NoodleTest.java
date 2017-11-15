package com.danshima.noodleapp;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

/**
 * Created by Giddy on 15/11/2017.
 */
public class NoodleTest {
    @Before
    public void setUp() throws Exception {
        Noodle noodleOne = new Noodle("Spicy Ramen");
        Noodle noodleTwo = new Noodle("Instant Noodles", "Not healthy", R.drawable.spicydandan, "At home", 2);
        noodleOne.getName();
        noodleOne.getDate();
    }

    @Test
    public void getName(){
        assertEquals("Spicy Ramen", "Spicy Ramen");
    }

    @Test
    public void getDate(){
        assertEquals("11/11/17", "11/11/17");
    }

        

}