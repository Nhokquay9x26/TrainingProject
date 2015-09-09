package vn.asiantech.LearingEnglish.fragments;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.TestingAdapter;

public class FragmentPageTesting extends BaseFragment {
    int fragNum;
    String[] mQuestion;
    private static TestingAdapter.OnChangePager mListener;

    public static FragmentPageTesting init(int val, TestingAdapter.OnChangePager listener) {
        FragmentPageTesting mFragmentPageTesting = new FragmentPageTesting();

        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        mFragmentPageTesting.setArguments(args);
        mListener = listener;
        return mFragmentPageTesting;
    }

    /**
     * Retrieving this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragNum = getArguments() != null ? getArguments().getInt("val") : 1;
        fragNum++;
        mQuestion = getResources().getStringArray(R.array.question_array);
    }

    /**
     * The Fragment's UI is a simple text view showing its instance number and
     * an associated list.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.fragment_testing,
                container, false);
        View tvQuestion = layoutView.findViewById(R.id.tvQuestion);
        View tvPageCurrent = layoutView.findViewById(R.id.tvPageCurrent);
        View tvPageTotal = layoutView.findViewById(R.id.tvPageTotal);
        ((TextView) tvQuestion).setText(Html.fromHtml("<b>" + "<font color=\"#ed166a\">"
                + "Câu " + fragNum + ": " + "</font>" + "<b/>" + mQuestion[fragNum - 1]));
        ((TextView) tvPageCurrent).setText("" + fragNum + "-");
        ((TextView) tvPageTotal).setText("5");

        ImageView imgBack = (ImageView) layoutView.findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListener.onChange(-1);
            }
        });
        ImageView imgNext = (ImageView) layoutView.findViewById(R.id.imgNext);
        imgNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListener.onChange(1);
            }
        });
        return layoutView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}