package com.danshima.noodleapp;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Giddy on 15/11/2017.
 */
public class NoodleTest {
    @Before
    public void setUp() throws Exception {
        Noodle noodleOne = new Noodle("Instant Noodles", "Not healthy", R.drawable.spicydandan, "At home", 2);
        noodleOne.getName();
        noodleOne.getSuggestedRestaurant();
    }

    @Test
    public void getName(){
        assertEquals("Spicy Ramen", "Spicy Ramen");
    }

    @Test
    public void getSuggestedRestaurant(){
        Noodle noodleTwo = new Noodle("Instant Noodles", "Not healthy", R.drawable.spicydandan, "At home", 2);
        assertEquals("At home", noodleTwo.getSuggestedRestaurant());
    }



}