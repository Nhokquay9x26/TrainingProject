package vn.asiantech.LearingEnglish.container;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.fragments.BaseContainerFragment;
import vn.asiantech.LearingEnglish.fragments.FavoriteFragment;

public class FavoriteContainer extends BaseContainerFragment {

    private boolean mIsViewInited;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return View.inflate(getActivity(), R.layout.container_fragment, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (!mIsViewInited) {
            mIsViewInited = true;
            initView();
        }
    }

    private void initView() {
        replaceFragment(new FavoriteFragment(), false);
    }

    @Override
    public Fragment getChildFragment() {
        return getChildFragmentManager().findFragmentById(R.id.container_framelayout);
    }
}
