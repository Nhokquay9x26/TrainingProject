package vn.asiantech.LearingEnglish.fragments;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.TestAdapter;

/**
 * Created by chanh on 09/09/2015.
 */


@EFragment(R.layout.ratings_custom_viewpaper)
public class TestPagerFragment extends BaseFragment {
    int NumFragment;
    String[] mQuestion;

    @ViewById(R.id.imgTestBack)
    ImageView imgBack;
    @ViewById(R.id.imgTestAdvance)
    ImageView imgNext;
    @ViewById(R.id.tvTestBack)
    TextView tvTestBack;
    @ViewById(R.id.tvViewpagerQuestion)
    TextView tvQuestion;
    @ViewById(R.id.tvTestAdvance)
    TextView tvTestAdvance;

    private static TestAdapter.OnChangePager mListerner;

    public static TestPagerFragment init(int val, TestAdapter.OnChangePager listener) {

        TestPagerFragment_ mFragmentPageTesting = new TestPagerFragment_();
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
        mQuestion = getResources().getStringArray(R.array.question_array);
    }


    @AfterViews
    public void AfterViews() {

        tvQuestion.setText(Html.fromHtml("<b>" + "<font color=\"#ed166a\">"
                + "Câu " + NumFragment + ": " + "</font>" + "<b/>" + mQuestion[NumFragment - 1]));

        tvTestBack.setText("" + NumFragment);
        tvTestAdvance.setText("10");

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Click(R.id.imgTestBack)
    public void onClickBack() {
            mListerner.onChange(TestFragment_.BACK);
        }

    @Click(R.id.imgTestAdvance)
    public void onClickNext() {
        mListerner.onChange(TestFragment_.NEXT);
    }
}
