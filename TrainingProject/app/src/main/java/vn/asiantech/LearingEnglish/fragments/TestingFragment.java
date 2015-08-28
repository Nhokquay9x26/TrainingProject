package vn.asiantech.LearingEnglish.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.core.fragments.Fragment;

/**
 * Created by tantv on 28/08/2015.
 */
public class TestingFragment extends Fragment {
    private View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.abc_action_bar_title_item,container,false);
        return mView;
    }
}
