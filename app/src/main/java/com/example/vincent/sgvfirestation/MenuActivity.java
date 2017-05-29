package com.example.vincent.sgvfirestation;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.vincent.sgvfirestation.Firebase.FirebaseManager;
import com.example.vincent.sgvfirestation.models.MenuListItem;
import com.example.vincent.sgvfirestation.tableSections.MenuTableSections;
import com.example.vincent.sgvfirestation.utils.HeaderListView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

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

    AlertDialog.Builder builder;
    AlertDialog loadingDialog;

    MenuTableSections menuTableSections;

    MenuListItem menuListItem;
    MenuListAdapter menuListAdapter;
    ArrayList<MenuTableSections> tableSections = new ArrayList<>();
    AnimationDrawable loadingAnimation;

    boolean isInitialized;

    public interface OnMenuItemClickedListener {
        void onMenuItemClicked(MenuListItem menuListItem);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        super.initialize();
        ButterKnife.bind(this);

        isInitialized = false;
        toolbar.setTitle(getTitle());
        menuHeaderListView = (HeaderListView) findViewById(R.id.menuHeaderListView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        builder = new AlertDialog.Builder(this);


        RelativeLayout loadingLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.loading_dialog_layout, null);
        TextView loadingAnimationTextView = (TextView) loadingLayout.findViewById(R.id.loadingAnimationTextView);
        loadingAnimation = (AnimationDrawable) loadingAnimationTextView.getBackground();
        builder.setView(loadingLayout);
        builder.setTitle(getString(R.string.welcomeText));
        loadingDialog = builder.show();
        loadingAnimation.start();

        setupFirebaseListeners();
    }

    private void onMenuClickedListener(){
        menuListAdapter.onMenuItemClickedListener = new OnMenuItemClickedListener() {
            @Override
            public void onMenuItemClicked(MenuListItem menuListItem) {
                Intent intent = new Intent(MenuActivity.this, ItemDetailScrollingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(getString(R.string.menuItemId), menuListItem.getItemId() + "");
                bundle.putString(getString(R.string.menuTypeIdentifier), menuListItem.getItemType());
                bundle.putString(getString(R.string.menuItemTitle), menuListItem.getItemName());

                intent.putExtras(bundle);

                startActivity(intent);
            }
        };
    }

    private void setupFirebaseListeners() {

        FirebaseManager.ourInstance().getMenuReference().addValueEventListener(new ValueEventListener() {  // menuListAdapter
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot subMenu : dataSnapshot.getChildren()) {// indica
                    MenuTableSections menuTableSections1 = new MenuTableSections(subMenu.getKey());
                    for (DataSnapshot items : subMenu.getChildren()) {    // items
                        menuTableSections1.addMenuItem(items.getValue(MenuListItem.class)).setItemId(Integer.parseInt(items.getKey()));
                    }
                    tableSections.add(menuTableSections1);
                    if(isInitialized)
                        ((MenuListAdapter) menuHeaderListView.getListView().getAdapter()).notifyDataSetChanged();
                }

                if(!isInitialized) {
                    populateTableSections();
                    MenuTableSections temp = tableSections.get(tableSections.size()-1);
                    FirebaseManager.menuItemIdIncrementor = temp.getSectionData().get(temp.getSectionData().size()-1).getItemId();
                    Log.d(TAG,"LastIndexOfLastItemLoaded: " + temp.getSectionData().get(temp.getSectionData().size()-1).getItemId());
                    loadingDialog.dismiss();
                    onMenuClickedListener();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void populateTableSections() {
//        ArrayList<MenuTableSections> tableSections = new ArrayList<>();


        MenuTableSections menuTableSections = new MenuTableSections("Indica");

        for (int j = 0; j < 10; j++) {
            MenuListItem menuListItem =
                    new MenuListItem("Gorilla Glue", AppConstants.lowTier, "Indica", "Some bud");
            menuTableSections.getSectionData().add(menuListItem);

           // FirebaseManager.ourInstance().getIndicaMenuReference().child(menuListItem.getItemId() + "").setValue(menuListItem);

            Log.d(TAG, "added " + menuListItem.getItemId());
        }
        tableSections.add(menuTableSections);


        menuListAdapter = new MenuListAdapter();
        menuListAdapter.setup(this);
        menuListAdapter.setupData(tableSections);
        menuHeaderListView.setAdapter(menuListAdapter);
        menuHeaderListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "clicked");
            }
        });
        menuListAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        menu.findItem(R.id.action_add).setVisible(true);
        menu.findItem(R.id.action_search).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_add:
                Log.d(TAG, "add button");
                return true;
            case R.id.action_search:
                Log.d(TAG, "search button");
                return true;
        }



        return false;
    }
}
