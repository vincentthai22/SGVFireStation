package com.thai.vincent.sgvfirestation;



import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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


import com.thai.vincent.sgvfirestation.Firebase.FirebaseManager;
import com.thai.vincent.sgvfirestation.models.MenuListItem;
import com.thai.vincent.sgvfirestation.tableSections.MenuTableSections;
import com.thai.vincent.sgvfirestation.utils.HeaderListView;
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
    TextView previousClickedToolbarTextView, currentClickedToolbarTextView;

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

        currentClickedToolbarTextView = (TextView) scrollView.findViewById(R.id.toolbar_all_text_view);


        RelativeLayout loadingLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.loading_dialog_layout, null);
        TextView loadingAnimationTextView = (TextView) loadingLayout.findViewById(R.id.loadingAnimationTextView);
        loadingAnimation = (AnimationDrawable) loadingAnimationTextView.getBackground();
        builder.setView(loadingLayout);
        builder.setTitle(getString(R.string.welcomeText));
        loadingDialog = builder.show();
        loadingAnimation.start();

        setupFirebaseListeners();
    }

    private void onMenuClickedListener() {
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

                    for(MenuTableSections menuTableSections : tableSections)
                        if(menuTableSections.getTitle().equals(subMenu.getKey()))
                            continue;

                    menuTableSections = menuTableSections1;
                    for (DataSnapshot items : subMenu.getChildren()) {    // items

                        MenuListItem item;
                        item = menuTableSections1.addMenuItem(items.getValue(MenuListItem.class));
                        item.setItemId(Integer.parseInt(items.getKey()));

                        if (item.getPriceTier().equals(AppConstants.lowTier))
                            menuTableSections1.lowTierCount++;
                        else if (item.getPriceTier().equals(AppConstants.midTier))
                            menuTableSections1.midTierCount++;
                        else
                            menuTableSections1.highTierCount++;

                    }
                    tableSections.add(menuTableSections1);
                    Log.d(TAG, "added");
                    if (isInitialized)
                        ((MenuListAdapter) menuHeaderListView.getListView().getAdapter()).notifyDataSetChanged();
                }

                if (!isInitialized) {
                    isInitialized = true;
                    populateTableSections();
                    MenuTableSections temp = tableSections.get(tableSections.size() - 1);
                    FirebaseManager.menuItemIdIncrementor = temp.getSectionData().get(temp.getSectionData().size() - 1).getItemId();
                    Log.d(TAG, "LastIndexOfLastItemLoaded: " + temp.getSectionData().get(temp.getSectionData().size() - 1).getItemId());
                    loadingDialog.dismiss();
                    onMenuClickedListener();
//                    for(MenuListItem menuListItem : menuTableSections.getSectionData()) {
//                        menuListItem.setDescription(getString(R.string.dummyDescriptionText));                                                    THIS IS HOW YOU MODIFY DATA. TEST APPROVED.
//                        FirebaseManager.ourInstance().getIndicaMenuReference().child(menuListItem.getItemId()+"").setValue(menuListItem);
//                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void populateTableSections() {
//        ArrayList<MenuTableSections> tableSections = new ArrayList<>();
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

        switch (item.getItemId()) {
            case R.id.action_add:
                Log.d(TAG, "add button");
                Intent intent = new Intent(this, NewItemActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_search:
                Log.d(TAG, "search button");
                return true;
        }


        return false;
    }

    public void onClickHandler(View view) {
        previousClickedToolbarTextView = currentClickedToolbarTextView;
        previousClickedToolbarTextView.setTextColor(ContextCompat.getColor(this, R.color.lightGray));
        currentClickedToolbarTextView = (TextView) view;
        currentClickedToolbarTextView.setTextColor(ContextCompat.getColor(this, android.R.color.white));


        switch (view.getId()) {
            case R.id.toolbar_all_text_view:
                menuListAdapter.isShowingAll = true;
                menuListAdapter.notifyDataSetChanged();
                break;
            case R.id.toolbar_sativa_text_view:
            case R.id.toolbar_hybrid_text_view:
            case R.id.toolbar_indica_text_view:
                for(int i = 0 ; i < tableSections.size(); i++) {
                    if(tableSections.get(i).getTitle().equals(currentClickedToolbarTextView.getText().toString().toLowerCase())) {
                        menuListAdapter.visibleSectionIndex = i;
                        Log.d(TAG, "visible section index is " + i);
                        break;
                    }
                }
                menuListAdapter.isShowingAll = false;
                menuListAdapter.notifyDataSetChanged();
                currentClickedToolbarTextView = (TextView) view;
                currentClickedToolbarTextView.setTextColor(ContextCompat.getColor(this, android.R.color.white));
            case R.id.toolbar_wax_text_view:
                break;
            case R.id.toolbar_edibles_text_view:
                break;
            case R.id.toolbar_moon_rocks_text_view:
                break;
            case R.id.toolbar_concentrates_text_view:
                break;
            case R.id.toolbar_pens_text_view:
                break;
            case R.id.toolbar_prerolls_text_view:
                break;
        }
    }

}
