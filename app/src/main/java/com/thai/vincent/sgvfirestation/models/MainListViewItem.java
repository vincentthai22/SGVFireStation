package com.thai.vincent.sgvfirestation.models;
/**
 * Created by Vincent on 4/29/2017.
 */

public class MainListViewItem {

    private String imageName;
    private String title;

    public MainListViewItem(String pTitle,String pImageName)
    {
        title = pTitle;
        imageName = pImageName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
