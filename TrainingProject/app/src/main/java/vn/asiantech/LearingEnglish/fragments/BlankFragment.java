package vn.asiantech.LearingEnglish.fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.core.fragments.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_blank)
public class BlankFragment extends Fragment {


    public BlankFragment() {
        // Required empty public constructor
    }


    @AfterViews
    void afterView(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
    }


}
