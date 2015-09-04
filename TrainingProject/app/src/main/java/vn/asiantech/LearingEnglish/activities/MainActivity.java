package vn.asiantech.LearingEnglish.activities;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.container.SettingContainer;
import vn.asiantech.LearingEnglish.container.FavoriteContainer;
import vn.asiantech.LearingEnglish.container.TabOneContainer;
import vn.asiantech.LearingEnglish.container.TabTwoContainer;
import vn.asiantech.LearingEnglish.fragments.BaseContainerFragment;
import vn.asiantech.LearingEnglish.views.HackyViewPager;
import vn.asiantech.LearingEnglish.views.HeaderBar;
import vn.asiantech.LearingEnglish.views.PagerSlidingTabStrip;


@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActionBarActivity implements HeaderBar.OnHeaderBarListener{

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private final String TAG = MainActivity.this.getClass().getName();
    // ViewPagercustomDrawable
    // ViewPager
    @ViewById(R.id.viewPager)
    protected HackyViewPager mPager;
    // SlidingTab Trip
    @ViewById(R.id.tabs)
    protected PagerSlidingTabStrip mTabs;
    @ViewById(R.id.framelayoutHeader)
    protected FrameLayout mFramelayoutHeader;
    private Drawable customDrawable;
    private boolean mDoubleBackToExitPressedOnce;
    private PageAdapter mAdapter;

    /*
    * init header
    */
    protected HeaderBar headerBar;

    @AfterViews
    void afterView() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View customView = inflater.inflate(R.layout.custom_actionbar, null);
        // Initilization
        headerBar = (HeaderBar) customView.findViewById(R.id.header_bar);
        headerBar.setOnHeaderBarListener(this);
        mFramelayoutHeader.addView(customView);
        setValue();
        setEvent();
    }

    private void setValue() {
        mAdapter = new PageAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        mTabs.setViewPager(mPager);
        mPager.setOffscreenPageLimit(4);
        setHeader(getResources().getString(R.string.tab_1));
    }

    private void setEvent() {
        mTabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                String title;
                if (position == 0) {
                    title = getResources().getString(R.string.tab_1);
                } else if (position == 1) {
                    title = getResources().getString(R.string.tab_2);
                } else if (position == 2) {
                    title = getResources().getString(R.string.tab_3);
                } else {
                    title = getResources().getString(R.string.tab_setting);
                }
                setHeader(title);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        backPressed();
    }

    private void backPressed() {
        boolean isPopFragment = false;
        BaseContainerFragment f = getCurrentBaseFragment();

        if (f != null) {
            isPopFragment = f.popFragment();
        }

        if (!isPopFragment) {
            if (mDoubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.mDoubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mDoubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }

    }

    private BaseContainerFragment getCurrentBaseFragment() {
        return (BaseContainerFragment) mAdapter
                .instantiateItem(mPager, mPager.getCurrentItem());
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    // ADAPTER VIEWPAGER
    public class PageAdapter extends FragmentStatePagerAdapter implements PagerSlidingTabStrip.IconTabProvider {
        private static final int CUSTOM_ICON = -1;
        private final int[] ICONS = {R.drawable.selector_tab_one,
                R.drawable.selector_tab_two,
                R.drawable.selector_tab_three,
                R.drawable.selector_tab_four};

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "position: " + position;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new TabOneContainer();
                case 1:
                    return new TabTwoContainer();
                case 2:
                    return new FavoriteContainer();
                default:
                    return new SettingContainer();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            FragmentManager manager = ((Fragment) object).getFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.remove((Fragment) object);
            trans.commit();

            super.destroyItem(container, position, object);
        }

        @Override
        public Drawable getPageIconDrawable(int position) {
            if (ICONS[position] == CUSTOM_ICON) {
                return customDrawable;
            }
            return getResources().getDrawable(ICONS[position]);
        }
    }

    /*
    * set show/ hide view in header
    */
    private void setHeader(String title) {
        headerBar.setTitle(title);
    }
}