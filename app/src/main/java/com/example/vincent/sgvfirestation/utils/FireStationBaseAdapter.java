package com.example.vincent.sgvfirestation.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Vincent on 5/7/2017.
 */

public class FireStationBaseAdapter extends SectionAdapter {

    private ArrayList<Object> tableSections = new ArrayList<>();

    private Context context;
    private LayoutInflater inflater;
    private DecimalFormat df;

    public FireStationBaseAdapter(Context context, ArrayList<Object> tableSections){
        this.context = context;
        this.tableSections=tableSections;
    }

    @Override
    public int numberOfSections() {
        return tableSections.size();
    }

    @Override
    public int numberOfRows(int section) {
        return 0;
    }

    @Override
    public View getRowView(int section, int row, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public Object getRowItem(int section, int row) {
        return null;
    }
}
