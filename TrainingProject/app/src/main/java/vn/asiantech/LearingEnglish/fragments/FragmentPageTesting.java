package vn.asiantech.LearingEnglish.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import vn.asiantech.LearingEnglish.R;

public class FragmentPageTesting extends BaseFragment {
    int fragNum;
String[] mQuestion;
    public static FragmentPageTesting init(int val) {
        FragmentPageTesting mFragmentPageTesting = new FragmentPageTesting();

        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        mFragmentPageTesting.setArguments(args);
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
        mQuestion=getResources().getStringArray(R.array.question_array);
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
        //((TextView) tv).setText("Câu " + fragNum + ":");
        ((TextView) tvQuestion).setText(Html.fromHtml("<b>"+"<font color=\"#EC48B5\">"
                + "Câu " + fragNum +": "+ "</font>"+"<b/>"+mQuestion[fragNum-1]));
        ((TextView) tvPageCurrent).setText("" + fragNum + "-");
        ((TextView) tvPageTotal).setText("10");
        return layoutView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}