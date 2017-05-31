package com.thai.vincent.sgvfirestation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Vincent on 5/22/2017.
 */

public class BaseActivity extends AppCompatActivity {

    final static String TAG = BaseActivity.class.getSimpleName();

    protected boolean willSlideRight;

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.animator.animator_slide_right_half, R.animator.animator_slide_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            return true;
        } else if (id == android.R.id.home) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private static int activeCount = 0;

    protected Toolbar toolbar;

    protected TextView toolbarTitleTextView, toolbarBackButtonTextView;
    protected Button toolbarBackButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.animator.animator_slide_left, R.animator.animator_slide_left_half);
    }

    public void initialize() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_custom);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    public void onBackPressed() {
        this.finish();

    }

}



