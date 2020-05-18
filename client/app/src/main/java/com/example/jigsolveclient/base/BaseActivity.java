package com.example.jigsolveclient.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.core.app.TaskStackBuilder;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    public void setContentView(@LayoutRes final int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    protected final void hideKeyboard(final View view) {
        final InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onBackPressed() {
        if(!navigateUp()){
            super.onBackPressed();
        }
    }

    /*Check if activity has parent activity*/
    protected boolean navigateUp() {
        Intent upIntent = NavUtils.getParentActivityIntent(this);
        if(upIntent != null) {
            if(NavUtils.shouldUpRecreateTask(this,upIntent) || isTaskRoot()){
                TaskStackBuilder.create(this).addNextIntentWithParentStack(upIntent).startActivities();
                return true;
            }
        }
        return false;
    }

    @Override
    public void showError(String title, String name) { }

    @Override
    public Context getContext() { return this; }
}
