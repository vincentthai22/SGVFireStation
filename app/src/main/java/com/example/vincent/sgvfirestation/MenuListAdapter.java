package com.example.vincent.sgvfirestation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vincent.sgvfirestation.models.MenuListItem;
import com.example.vincent.sgvfirestation.models.MenuTableSections;
import com.example.vincent.sgvfirestation.utils.SectionAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Vincent on 5/7/2017.
 */

public class MenuListAdapter extends SectionAdapter {

    private ArrayList<MenuTableSections> tableSections;

    private Context context;
    private LayoutInflater inflater;
    private DecimalFormat df;

    public MenuActivity.OnMenuItemClickedListener onMenuItemClickedListener;

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

        MenuListItem menuListItem = getRowItem(section, row);

        MenuListAdapter.ViewHolderRowItem viewHolderRowItem;
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

        viewHolderRowItem.titleOfItemTextView.setText(menuListItem.getItemName());
        viewHolderRowItem.itemTypeTextView.setText(menuListItem.getItemType());
        viewHolderRowItem.concentrationValueTextView.setText(String.valueOf(menuListItem.getThcLevel()));
        viewHolderRowItem.smallerPriceTextView.setText(String.valueOf(menuListItem.getEightPrice()));
        viewHolderRowItem.largerPriceTextView.setText(String.valueOf(menuListItem.getQuadPrice()));
        // viewHolderRowItem.itemImageImageView.setImageIcon(menuListItem.getImage());


        return convertView;
    }

    @Override
    public boolean hasSectionHeaderView(int section) {
        return true;
    }

    @Override
    public MenuListItem getRowItem(int section, int row) {

        return tableSections.get(section).getSectionData().get(row);
    }

    @Override
    public void onRowItemClick(AdapterView<?> parent, View view, int section, int row, long id) {
        MenuListItem menuListItem = getRowItem(section,row);

        onMenuItemClickedListener.onMenuItemClicked(menuListItem);

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
