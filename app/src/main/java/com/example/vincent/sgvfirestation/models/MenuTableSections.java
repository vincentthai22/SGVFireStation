package com.example.vincent.sgvfirestation.models;

import java.util.ArrayList;

/**
 * Created by Vincent on 5/7/2017.
 */

public class MenuTableSections {

    private String title;
    private ArrayList<MenuItem> sectionData;

    private Object sectionTag;

    private Boolean expanded = false;

    public MenuTableSections(){
        sectionData = new ArrayList<>();
        title = "";

    }

    public void addMenuItem(MenuItem menuItem){
        sectionData.add(menuItem);
    }
}
