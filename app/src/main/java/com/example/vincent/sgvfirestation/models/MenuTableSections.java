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

    public MenuTableSections(String title){
        sectionData = new ArrayList<>();
        this.title = title;

    }

    public void addMenuItem(MenuItem menuItem){
        sectionData.add(menuItem);
    }


    public ArrayList<MenuItem> getSectionData() {
        return sectionData;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
