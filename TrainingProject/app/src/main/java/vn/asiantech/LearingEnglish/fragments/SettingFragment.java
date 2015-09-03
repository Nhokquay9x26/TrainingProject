package vn.asiantech.LearingEnglish.fragments;

import android.app.Activity;
import android.widget.ImageButton;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;


@EFragment(R.layout.fragment_setting)
public class SettingFragment extends BaseFragment {
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Click(R.id.imgBtnNextTopSetting)
    void clickTopSetting(){
            replaceFragment(new TopPointFragment_());
    }

}