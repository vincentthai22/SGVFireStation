package com.example.vincent.sgvfirestation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vincent.sgvfirestation.models.MainListViewItem;

import java.util.ArrayList;

/**
 * Created by Vincent on 4/29/2017.
 */

public class MainListViewAdapter extends BaseAdapter {

    private static String TAG = "TrackingModuleAdapter";

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<MainListViewItem> mModules;

    private int ViewType_Photo = 0;
    private int ViewType_Tracking = 1;
    private AdapterView.OnItemClickListener listener;


    public MainListViewAdapter(Context context)
    {
        final ArrayList<MainListViewItem> moduleList = new ArrayList(5);
        moduleList.add(new MainListViewItem("Photo","photo"));
        moduleList.add(new MainListViewItem("Menu","marijane_leaf"));
        moduleList.add(new MainListViewItem("Deals","deals"));
        moduleList.add(new MainListViewItem("Settings","nursing"));
        moduleList.add(new MainListViewItem("About us","pumping"));
        moduleList.add(new MainListViewItem("Bath Time","bathtime"));
        moduleList.add(new MainListViewItem("Bottle","bottle"));
        moduleList.add(new MainListViewItem("Diaper","diaper"));
        moduleList.add(new MainListViewItem("Medication","medication"));

        mContext = context;
        mModules = moduleList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnClickListener(AdapterView.OnItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public int getCount(){
        return mModules.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mModules.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public int getViewTypeCount()
    {
        return 2;
    }

    @Override
    public int getItemViewType(int position)
    {
        MainListViewItem mainListViewItem = (MainListViewItem) getItem(position);

        if (mainListViewItem.getTitle().equals("Photo"))
        {
            return ViewType_Photo;
        }

        return ViewType_Tracking;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        MainListViewItem mainListViewItem = (MainListViewItem) getItem(position);

        if (mainListViewItem.getTitle().equals("Photo"))
        {
            ViewHolderTrackingModule viewHolder;

            if (null == convertView)
            {
                convertView = mInflater.inflate(R.layout.list_item_tracking_photo, parent, false);
                viewHolder = new ViewHolderTrackingModule();
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.background_image_view);
                viewHolder.titleTextView = (TextView) convertView.findViewById(R.id.background_name_text_view);
                viewHolder.textView2 = (TextView) convertView.findViewById(R.id.background_info_text_view);
                convertView.setTag(viewHolder);
            }
            else
            {
                viewHolder = (ViewHolderTrackingModule)convertView.getTag();
            }

            viewHolder.titleTextView.setText(mContext.getString(R.string.main_screen_title));
            viewHolder.textView2.setText("Located somewhere in Rowland Heights");

            return convertView;
        }
        else
        {
            ViewHolderTrackingModule viewHolder;

            if (null == convertView)
            {
                convertView = mInflater.inflate(R.layout.list_item_tracking_module, parent, false);

                viewHolder = new ViewHolderTrackingModule();
                viewHolder.titleTextView = (TextView) convertView.findViewById(R.id.titleTextView);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);



                convertView.setTag(viewHolder);
            }
            else
            {
                viewHolder = (ViewHolderTrackingModule) convertView.getTag();
            }

            viewHolder.titleTextView.setText(mainListViewItem.getTitle());

            int id = mContext.getResources().getIdentifier(mainListViewItem.getImageName(),"drawable",mContext.getPackageName());

            viewHolder.imageView.setImageResource(id);

            return convertView;
        }
    }


    private static class ViewHolderTrackingModule
    {
        public TextView titleTextView;
        public TextView textView2;
        public ImageView imageView;
    }

}

