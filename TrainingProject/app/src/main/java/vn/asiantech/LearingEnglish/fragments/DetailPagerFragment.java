package vn.asiantech.LearingEnglish.fragments;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.DetailAdapter;
import vn.asiantech.LearingEnglish.adapter.TestAdapter;

/**
 * @author DaoQuocViet
 *         Created by nhokquay9x26 on 11/09/2015.
 */


@EFragment(R.layout.detail_custom_viewpaper)
public class DetailPagerFragment extends BaseFragment {
    int NumFragment;
    String[] mDetail;

    @ViewById(R.id.imgWord)
    ImageView imgWord;
    @ViewById(R.id.tvWord)
    TextView mTvWord;
    @ViewById(R.id.tvTestBack)
    TextView mTvTestBack;
    @ViewById(R.id.tvTrain)
    TextView mTvTrain;
    @ViewById(R.id.tvExample)
    TextView mTvExample;
    @ViewById(R.id.imgBack)
    ImageView mImgBack;
    @ViewById(R.id.imgNext)
    ImageView mImgNext;

    private static DetailAdapter.OnChangePager mListerner;

    public static DetailPagerFragment init(int val, DetailAdapter.OnChangePager listener) {

        DetailPagerFragment_ mFragmentPageTesting = new DetailPagerFragment_();
        Bundle args = new Bundle();
        args.putInt("val", val);
        mFragmentPageTesting.setArguments(args);
        mListerner = listener;
        return mFragmentPageTesting;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NumFragment = getArguments() != null ? getArguments().getInt("val") : 1;
        NumFragment++;
        mDetail = getResources().getStringArray(R.array.word_array);
    }

    @AfterViews
    public void AfterViews() {

        mTvWord.setText(Html.fromHtml(mDetail[NumFragment - 1]));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Click(R.id.imgTestBack)
    public void onClickBack() {
        mListerner.onChange(DetailFragment_.BACK);
    }

    @Click(R.id.imgTestAdvance)
    public void onClickNext() {
        mListerner.onChange(DetailFragment_.NEXT);
    }
}
