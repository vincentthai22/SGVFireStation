package com.example.vincent.sgvfirestation.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vincent.sgvfirestation.MenuActivity;
import com.example.vincent.sgvfirestation.R;
import com.example.vincent.sgvfirestation.models.MenuItem;
import com.example.vincent.sgvfirestation.models.MenuTableSections;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Vincent on 5/7/2017.
 */

public class FireStationBaseAdapter extends SectionAdapter {

    private ArrayList<MenuTableSections> tableSections;

    private Context context;
    private LayoutInflater inflater;
    private DecimalFormat df;

    public void setupData(ArrayList<MenuTableSections> tableSections) {
        this.tableSections = tableSections;
        notifyDataSetChanged();
    }



    public void setup(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int numberOfSections() {
        return tableSections.size()-1;
    }

    @Override
    public int numberOfRows(int section) {
        return tableSections.get(section).getSectionData().size();
    }

    @Override
    public View getRowView(int section, int row, View convertView, ViewGroup parent) {

        MenuItem menuItem = getRowItem(section, row);

        FireStationBaseAdapter.ViewHolderRowItem viewHolderRowItem;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_menu_item, parent,false);
            viewHolderRowItem = new ViewHolderRowItem();
            viewHolderRowItem.titleOfItemTextView = (TextView) convertView.findViewById(R.id.titleOfItemTextView);
            viewHolderRowItem.itemTypeTextView = (TextView) convertView.findViewById(R.id.itemTypeTextView);
            viewHolderRowItem.concentrationValueTextView = (TextView) convertView.findViewById(R.id.concentrationValueTextView);
            viewHolderRowItem.smallerPriceTextView = (TextView) convertView.findViewById(R.id.smallerPriceAmountTextView);
            viewHolderRowItem.largerPriceTextView = (TextView) convertView.findViewById(R.id.largerPriceTextView);
            viewHolderRowItem.itemImageImageView = (ImageView) convertView.findViewById(R.id.itemImageImageView);

        } else {
            viewHolderRowItem = (ViewHolderRowItem) convertView.getTag();
            return convertView;
        }

        viewHolderRowItem.titleOfItemTextView.setText(menuItem.getItemName());
        viewHolderRowItem.itemTypeTextView.setText(menuItem.getItemType());
        viewHolderRowItem.concentrationValueTextView.setText(String.valueOf(menuItem.getThcLevel()));
        viewHolderRowItem.smallerPriceTextView.setText(String.valueOf(menuItem.getEightPrice()));
        viewHolderRowItem.largerPriceTextView.setText(String.valueOf(menuItem.getQuadPrice()));
        // viewHolderRowItem.itemImageImageView.setImageIcon(menuItem.getImage());


        return convertView;
    }

    @Override
    public boolean hasSectionHeaderView(int section) {
        return true;
    }

    @Override
    public MenuItem getRowItem(int section, int row) {

        return tableSections.get(section).getSectionData().get(row);
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {

        if (convertView == null) {
            ViewHolderSectionItem viewHolderSectionItem = new ViewHolderSectionItem();
            convertView = inflater.inflate(R.layout.list_item_menu_header,parent,false);


            viewHolderSectionItem.itemTitleTextView = (TextView) convertView.findViewById(R.id.menuHeaderListViewHeaderTextView);


        } else {

        }

        return convertView;
    }

    private static class ViewHolderRowItem {

        TextView titleOfItemTextView;
        TextView itemTypeTextView;
        TextView smallerPriceTextView;
        TextView largerPriceTextView;
        TextView concentrationValueTextView;
        ImageView itemImageImageView;

    }

    private static class ViewHolderSectionItem {
        TextView itemTitleTextView;
    }

}
