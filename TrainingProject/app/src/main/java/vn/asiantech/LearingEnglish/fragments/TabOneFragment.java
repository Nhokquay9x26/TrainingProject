package vn.asiantech.LearingEnglish.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.TopAdapter;
import vn.asiantech.LearingEnglish.models.Top;


@EFragment(R.layout.fragment_top)
public class TabOneFragment extends BaseFragment {
    private ArrayList<Top> mListTop;
    @ViewById(R.id.rvListTop)
    RecyclerView mRvListTop;

    @AfterViews
    void AfterView() {
        setValue();
        TopAdapter adapter = new TopAdapter(this, mListTop);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRvListTop.setLayoutManager(layoutManager);
        mRvListTop.setAdapter(adapter);
    }

    private void setValue() {
        mListTop = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Top top = new Top(R.drawable.img_avatar, "Animal");
            mListTop.add(top);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}