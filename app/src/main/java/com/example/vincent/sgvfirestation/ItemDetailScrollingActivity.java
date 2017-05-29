package com.example.vincent.sgvfirestation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vincent.sgvfirestation.Firebase.FirebaseManager;
import com.example.vincent.sgvfirestation.models.MenuListItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vincent on 5/6/2017.
 */

public class ItemDetailScrollingActivity extends BaseActivity {

    private final static String TAG = ItemDetailScrollingActivity.class.getSimpleName();


    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;

    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;

    MenuListItem menuListItem;

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
        setTitle(getIntent().getExtras().getString(getString(R.string.menuItemTitle)));

        Log.d(TAG, AppConstants.ItemTypes.Indica.getName());

        if(menuTypeidentifier.equals(AppConstants.ItemTypes.Indica.getName())) {
            Log.d(TAG,"insideIF");
            FirebaseManager.ourInstance().getIndicaMenuReference().child(menuItemId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    menuListItem = dataSnapshot.getValue(MenuListItem.class);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }
}
