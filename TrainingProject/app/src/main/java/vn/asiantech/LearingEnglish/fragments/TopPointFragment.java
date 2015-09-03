package vn.asiantech.LearingEnglish.fragments;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.AdapterTopPoint;
import vn.asiantech.LearingEnglish.models.TopPoint;

/**
 * Created by sunday on 03/09/2015.
 */
@EFragment(R.layout.fragment_top_point)
public class TopPointFragment extends BaseFragment{
    private List<TopPoint> mTopPoints = new ArrayList<TopPoint>();

    @ViewById(R.id.recyclerViewTopPoint)
    RecyclerView recyclerViewTopPoint;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
    @AfterViews
    protected void AfterViews(){
        creatData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        AdapterTopPoint mAdapter = new AdapterTopPoint(mTopPoints, getActivity());
        //recyclerViewTopPoint.setHasFixedSize(true);
        recyclerViewTopPoint.setLayoutManager(linearLayoutManager);
        recyclerViewTopPoint.setAdapter(mAdapter);
    }
    public void creatData(){
        mTopPoints.add(new TopPoint("1nd", R.drawable.ico_avatar, "MatinerJack", 200));
        mTopPoints.add(new TopPoint("2nd", R.drawable.ico_avatar, "Emma Wilson", 100));
        mTopPoints.add(new TopPoint("3rd", R.drawable.ico_avatar, "Lavery Maiss", 30));
        mTopPoints.add(new TopPoint("4th", R.drawable.ico_avatar, "Lillie Watts", 10));
        mTopPoints.add(new TopPoint("5th", R.drawable.ico_avatar, "Sunday Sef", 5));

    }

}
