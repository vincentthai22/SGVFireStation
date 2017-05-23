package com.example.vincent.sgvfirestation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;

import com.example.vincent.sgvfirestation.models.MenuTableSections;
import com.example.vincent.sgvfirestation.utils.FireStationBaseAdapter;
import com.example.vincent.sgvfirestation.utils.HeaderListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vincent on 5/3/2017.
 */

public class MenuActivity extends BaseActivity {

    final static String TAG = MenuActivity.class.getSimpleName();

    @BindView(R.id.horizontalToolbarScrollview)
    HorizontalScrollView scrollView;

    @BindView(R.id.toolbar_custom)
    Toolbar toolbar;

    @BindView(R.id.menuHeaderListView)
    HeaderListView menuHeaderListView;

    MenuTableSections menuTableSections;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        toolbar.setTitle(getTitle());
        menuHeaderListView = (HeaderListView) findViewById(R.id.menuHeaderListView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        populateTableSections();



    }

    public void populateTableSections(){
        ArrayList<MenuTableSections> tableSections = new ArrayList<>();

        for(int i = 0 ; i < 10; i++) {
            MenuTableSections menuTableSections = new MenuTableSections("Indica");

            for(int j = 0 ; j < 10 ; j++){
                com.example.vincent.sgvfirestation.models.MenuItem menuItem =
                        new com.example.vincent.sgvfirestation.models.MenuItem("Gorilla Glue",AppConstants.lowTier,"Indica","Some bud");
                menuTableSections.getSectionData().add(menuItem);

                Log.d(TAG, "added");
            }
            tableSections.add(menuTableSections);
        }



        FireStationBaseAdapter adapter = new FireStationBaseAdapter();
        adapter.setup(this);
        adapter.setupData(tableSections);
        menuHeaderListView.setAdapter(adapter);
        menuHeaderListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"clicked");
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
