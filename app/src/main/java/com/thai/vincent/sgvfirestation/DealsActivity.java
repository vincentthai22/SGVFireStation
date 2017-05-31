package com.thai.vincent.sgvfirestation;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent on 5/30/2017.
 */

public class DealsActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deals);
    }

    private class DealsAdapter extends ArrayAdapter {
        private List<String> list;
        private int resource, headerResource;

        public DealsAdapter(Context context, int resource, int headerResource, List<String> list){
            super(context,resource, list);
            this.list = (ArrayList) list;
            this.resource = resource;
            this.headerResource = headerResource;

        }
        public DealsAdapter(Context context, int resource,  List<String> list){
            super(context,resource, list);
            this.list = (ArrayList) list;
            this.resource = resource;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            return createViewFromResource(position, convertView, parent, resource);
        }

        public String getItem(int position){
            return list.get(position);
        }

        public View createViewFromResource(int position, View convertView, ViewGroup parent, int resource){
            View view;
            LayoutInflater mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            TextView text, rightText;
            ImageView img;
            if(convertView == null)
                view = mInflater.inflate(resource,parent,false);
            else
                view = convertView;
            switch(resource) {




            }
            return view;
        }
    }
}
