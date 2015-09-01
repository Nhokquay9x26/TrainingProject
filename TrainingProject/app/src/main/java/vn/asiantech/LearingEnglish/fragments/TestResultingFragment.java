package vn.asiantech.LearingEnglish.fragments;


import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;

/**
 * Created by tantv on 01/09/2015.
 */

@EFragment(R.layout.fragment_test_resulting)
public class TestResultingFragment extends Fragment {
    @ViewById(R.id.recycleViewResult)
    RecyclerView mRecycleViewResult;

    @AfterViews
    void afterView() {
        configRecycleView();
    }

    private void configRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleViewResult.setLayoutManager(linearLayoutManager);
    }
}
