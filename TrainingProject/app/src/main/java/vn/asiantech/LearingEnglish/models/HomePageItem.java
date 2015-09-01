package vn.asiantech.LearingEnglish.models;

import android.support.v4.app.Fragment;

import lombok.AllArgsConstructor;


/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 8/27/15.
 */

@AllArgsConstructor(suppressConstructorProperties = true)
public class HomePageItem {
    private Fragment fragment;
    private String title;
    private int drawableResource;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public int getDrawableResource() {
        return drawableResource;
    }

    public void setDrawableResource(int drawableResource) {
        this.drawableResource = drawableResource;
    }
}
