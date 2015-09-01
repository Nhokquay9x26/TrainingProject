package vn.asiantech.LearingEnglish.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.eventbus.BusProvider;
import vn.asiantech.LearingEnglish.eventbus.PopToTopSameTabClickEvent;
import vn.asiantech.LearingEnglish.models.HomePageItem;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 8/27/15.
 */
public class PagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {
    private ArrayList<HomePageItem> tabItems = new ArrayList<>();
    private Fragment mContext;

    public PagerAdapter(Fragment context,
                        ArrayList<HomePageItem> items) {
        super(context.getChildFragmentManager());
        this.mContext = context;
        this.tabItems = items;
    }

    @Override
    public Fragment getItem(final int position) {
        Fragment item = tabItems.get(position).getFragment();
        return item;
    }

    @Override
    public int getCount() {
        return tabItems.size();
    }

    @Override
    public int getItemPosition(Object item) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabItems.get(position).getTitle();
    }

    @Override
    public int getPageIconResId(int i) {
        return tabItems.get(i).getDrawableResource();
    }

    @Override
    public View getView(int position) {
        View v = LayoutInflater.from(mContext.getActivity()).inflate(R.layout.custom_item_tab, null, false);
        TextView title = (TextView) v.findViewById(R.id.title);
        ImageView icon = (ImageView) v.findViewById(R.id.thumb);
//        TextView notify = (TextView) v.findViewById(R.id.tvNotificationCount);

        icon.setImageResource(tabItems.get(position).getDrawableResource());
        title.setText(tabItems.get(position).getTitle());
        return v;
    }

    @Override
    public void onTabClick(int position, boolean isSameTab) {
        if (isSameTab) {
            BusProvider.getInstance().post(new PopToTopSameTabClickEvent());
        }
    }

}
