package com.example.vincent.sgvfirestation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.vincent.sgvfirestation.models.MainListViewItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.main_list_view)
    ListView mMainListView;
    @BindView(R.id.toolbar_custom)
    Toolbar mToolBar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    MainListViewAdapter mMainListViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        mMainListViewAdapter = new MainListViewAdapter(this);

        mMainListView.setAdapter(mMainListViewAdapter);


        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                MainListViewItem mainListViewItem = (MainListViewItem) mMainListViewAdapter.getItem(position);
                Intent intent;

                Log.d(TAG, "clicked item is " + position);
                Log.d(TAG, "clicked item name is " + mainListViewItem.getTitle().toLowerCase());

                switch (mainListViewItem.getTitle().toLowerCase()) {
                    case "menu":
                        intent = new Intent(MainActivity.this, MenuActivity.class);
                        MainActivity.this.startActivity(intent);
                }

            }
        };

        mMainListView.setOnItemClickListener(listener);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
