package com.example.vincent.sgvfirestation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vincent.sgvfirestation.Firebase.FirebaseManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vincent on 5/6/2017.
 */

public class ItemDetailScrollingActivity extends BaseActivity {


    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;

    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        ButterKnife.bind(this);

        collapsingToolbarLayout = (CollapsingToolbarLayout) appBarLayout.findViewById(R.id.toolbar_layout);
        toolbar = (Toolbar) collapsingToolbarLayout.findViewById(R.id.toolbar_within_collapsing);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String menuItemId, menuTypeidentifier;
        menuItemId = getIntent().getExtras().getString(getString(R.string.menuItemId));
        menuTypeidentifier = getIntent().getExtras().getString(getString(R.string.menuTypeIdentifier));

        //FirebaseManager.ourInstance().

    }
}
