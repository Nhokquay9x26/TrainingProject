package vn.asiantech.LearingEnglish.fragments;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import lombok.ToString;
import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.TestingAdapter;

@EFragment(R.layout.fragment_testing)
public class FragmentPageTesting extends BaseFragment {

    private int mFragmentNumber;
    private String[] mQuestion;

    @ViewById(R.id.tvQuestion)
    TextView mTvQuestion;

    @ViewById(R.id.tvPageCurrent)
    TextView mTvPageCurrent;

    @ViewById(R.id.tvPageTotal)
    TextView mTvPageTotal;

    @Click(R.id.imgBack)
    void clickBack() {
        mListener.onChange(-1);
    }

    @Click(R.id.imgNext)
    void clickNext() {
        mListener.onChange(1);
    }

    private static TestingAdapter.OnChangePager mListener;

    public static FragmentPageTesting init(int page, TestingAdapter.OnChangePager listener) {

        FragmentPageTesting_ mFragmentPageTesting = new FragmentPageTesting_();
        Bundle bundle = new Bundle();
        bundle.putInt("page", page);
        mFragmentPageTesting.setArguments(bundle);
        mListener = listener;
        return mFragmentPageTesting;
    }

    /**
     * Retrieving this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentNumber = getArguments() != null ? getArguments().getInt("page") : 1;
        mFragmentNumber++;
        mQuestion = getResources().getStringArray(R.array.question_array);
    }

    /**
     * The Fragment's UI is a simple text view showing its instance number and
     * an associated list.
     */
    @AfterViews
    public void AfterViews() {
        mTvQuestion.setText(Html.fromHtml("<b>" + "<font color=\"#ed166a\">"
                + "Câu " + mFragmentNumber + ": " + "</font>" + "<b/>" + mQuestion[mFragmentNumber - 1]));
        mTvPageCurrent.setText("" + mFragmentNumber + "-");
        mTvPageTotal.setText(Integer.toString(mQuestion.length));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}