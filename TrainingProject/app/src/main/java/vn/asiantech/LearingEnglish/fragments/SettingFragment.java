package vn.asiantech.LearingEnglish.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.SettingAdapter;
import vn.asiantech.LearingEnglish.models.SettingModel;

/**
 * @author mrson
 * Created by mrson on 04/09/2015.
 */
@EFragment(R.layout.fragment_setting)
public class SettingFragment extends BaseFragment{
    @ViewById(R.id.recycleViewSetting)
    RecyclerView recyclerViewSetting;
    private final List<SettingModel> settingModels = new ArrayList<>();
    @AfterViews
    void afterViews(){
        fakeData();
        SettingAdapter settingAdapter = new SettingAdapter(getActivity(), settingModels);
        recyclerViewSetting.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        recyclerViewSetting.setAdapter(settingAdapter);}
    private void fakeData(){
        SettingModel settingModel = new SettingModel();
        settingModel.setProfilNameSetting("Nguyen thai Son");
        settingModel.setProfileSetting(R.drawable.img_profile_test);
        settingModels.add(settingModel);

    }


}
