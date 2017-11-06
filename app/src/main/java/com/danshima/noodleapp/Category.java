package com.danshima.noodleapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giddy on 05/11/2017.
 */

public class Category {
    private Noodle noodle;
    private List<Noodle> japaneseList;
    private List<Noodle> vietnameseList;
    private List<Noodle> koreanList;
    private List<Noodle> chineseList;
    private List<Noodle> thaiList;

    public Category() {
        japaneseList = new ArrayList<>();
        vietnameseList = new ArrayList<>();
        koreanList = new ArrayList<>();
        chineseList = new ArrayList<>();
        thaiList = new ArrayList<>();
    }

    public void addToANoodleList() {
        if (noodle.getCategory().equals("japanese")) {
            japaneseList.add(noodle);
        }
        if (noodle.getCategory().equals("vietnamese")) {
            vietnameseList.add(noodle);
        }
        if (noodle.getCategory().equals("korean")) {
            koreanList.add(noodle);
        }
        if (noodle.getCategory().equals("thai")) {
            thaiList.add(noodle);
        }
        if (noodle.getCategory().equals("chinese")) {
            chineseList.add(noodle);
        }
        /*switch (noodle.getCategory()) {
            case "japanese":
                japaneseList.add(noodle);
                break;
            case "vietnamese":
                vietnameseList.add(noodle);
        }*/


    }


}
