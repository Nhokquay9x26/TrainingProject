package vn.asiantech.LearingEnglish.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.asiantech.LearingEnglish.R;

/**
 * Created by ThanhITBK on 9/9/2015.
 */
public class FragmentAnswer extends Fragment {
    int fragNum;
    String arr[] = {"This is", "a Truiton", "Demo", "App", "For", "Showing",
            "FragmentPagerAdapter", "and ViewPager", "Implementation"};

    public static FragmentAnswer init(int val) {
        FragmentAnswer truitonList = new FragmentAnswer();

        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        truitonList.setArguments(args);

        return truitonList;
    }

    /**
     * Retrieving this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragNum = getArguments() != null ? getArguments().getInt("val") : 1;
    }

    /**
     * The Fragment's UI is a simple text view showing its instance number and
     * an associated list.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.fragment_answer,
                container, false);
        RecyclerView mRecyclerView = (RecyclerView) layoutView.findViewById(R.id.rvAnswer);
        return layoutView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        setListAdapter(new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1, arr));
    }

}
