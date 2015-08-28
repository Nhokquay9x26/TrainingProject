package vn.asiantech.LearingEnglish.models;

import android.support.v4.app.Fragment;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.asiantech.LearingEnglish.fragments.TopFragment;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 8/27/15.
 */
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class HomePageItem {
    private Fragment fragment;
    private String title;
    private int drawableResource;

}
