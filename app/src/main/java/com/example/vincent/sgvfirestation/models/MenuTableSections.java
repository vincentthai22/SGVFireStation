package com.example.vincent.sgvfirestation.models;

import java.util.ArrayList;

/**
 * Created by Vincent on 5/7/2017.
 */

public class MenuTableSections {

    private String title;

    private ArrayList<MenuListItem> sectionData;

    private Object sectionTag;

    private Boolean expanded = false;

    public MenuTableSections(String title){
        sectionData = new ArrayList<>();
        this.title = title;

    }

    public void addMenuItem(MenuListItem menuListItem){
        sectionData.add(menuListItem);
    }


    public ArrayList<MenuListItem> getSectionData() {
        return sectionData;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
