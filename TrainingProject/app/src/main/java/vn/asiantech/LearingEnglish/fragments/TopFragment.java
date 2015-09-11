package vn.asiantech.LearingEnglish.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.TopAdapter;
import vn.asiantech.LearingEnglish.container.TopContainer;
import vn.asiantech.LearingEnglish.models.Top;


@EFragment(R.layout.fragment_top)
public class TopFragment extends BaseFragment implements TopAdapter.OnTopListener {
    private ArrayList<Top> mListTop;
    @ViewById(R.id.rvListTop)
    RecyclerView mRvListTop;

    @AfterViews
    void AfterView() {
        setValue();
        TopAdapter adapter = new TopAdapter(this, mListTop, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRvListTop.setLayoutManager(layoutManager);
        mRvListTop.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mOnBaseFragmentListener != null) {
            mOnBaseFragmentListener.setHeaderTitle(getResources().getString(R.string.tab_top));
        }
    }

    private void setValue() {
        mListTop = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Top top = new Top(R.drawable.img_avatar, "Animal " + (i + 1));
            mListTop.add(top);
        }
    }

    @Override
    public void OnClickItem(int position) {
        android.support.v4.app.Fragment fragmentParent = getParentFragment();
        if (fragmentParent instanceof TopContainer) {
            WordDetailContainerFragment_ fragment = new WordDetailContainerFragment_();
            Bundle bundle = new Bundle();
            bundle.putString("title", mListTop.get(position).getCategory());
            fragment.setArguments(bundle);
            ((TopContainer) fragmentParent).replaceFragment(fragment, true);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}