package com.my.televip.settings.controller;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.my.televip.settings.ui.SettingsActivity;
import com.my.televip.logging.Logger;
import com.my.televip.virtuals.ui.LaunchActivity;

public class SettingsController {

    public FrameLayout settingsView;
    private final Context context;

    public SettingsController(Context context) {
        this.context = context;
    }

    public void openView(){
        try {
            if (settingsView == null) {
                settingsView = new FrameLayout(context);
            }

            settingsView.removeAllViews();
            SettingsActivity settingsActivity = new SettingsActivity(context);
            settingsActivity.showDialog(this);

            settingsView.addView(settingsActivity.createView(this));

            show(settingsView);
        } catch (Throwable e) {
            Logger.e(e);
        }
    }

    public void show(View target) {
        LaunchActivity launchActivity = new LaunchActivity(context);
        if (target.getParent() == null) {
            launchActivity.frameLayout.addView(target);
        }

        for (int i = 0; i < launchActivity.frameLayout.getChildCount(); i++) {
            View child = launchActivity.frameLayout.getChildAt(i);
            child.setVisibility(child == target ? View.VISIBLE : View.GONE);
        }

        target.bringToFront();
    }

    public void hide() {
        LaunchActivity launchActivity = new LaunchActivity(settingsView.getContext());
        for (int i = 0; i < launchActivity.frameLayout.getChildCount(); i++) {
            View child = launchActivity.frameLayout.getChildAt(i);
            child.setVisibility(child == settingsView ? View.GONE : View.VISIBLE);
        }

        if (settingsView != null && settingsView.getParent() != null) {
            launchActivity.frameLayout.removeView(settingsView);
        }
        SettingsActivity.isSettings = false;
    }


}
