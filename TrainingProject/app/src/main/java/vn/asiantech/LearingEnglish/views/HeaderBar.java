
package vn.asiantech.LearingEnglish.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import vn.asiantech.LearingEnglish.R;

/**
 * HeaderBar is a sub class of {@link RelativeLayout}. HeaderBar is used to
 * apply for all action bar in this application
 */
public class HeaderBar extends RelativeLayout implements OnClickListener {

    private Context mContext;
    private OnHeaderBarListener mOnHeaderBarListener;

    private TextView mTvTitleHeader;

    public enum HeaderBarType {
        TYPE_HOME
    }


    // interface callback function to MainActivity
    public HeaderBar(Context context) {
        super(context);
        mContext = context;
        init(context);
        setValueDefault();
        setEvent();
    }

    public HeaderBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(context);
        setValueDefault();
        setEvent();
    }

    /**
     * This method is used to init all views
     *
     * @param context
     */

    private void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(
                R.layout.header_bar, this, false);

        mTvTitleHeader = (TextView) rootView.findViewById(R.id.tvTitleHeader);

        this.addView(rootView);
    }


    /**
     * This method is used to init value default for view
     */
    private void setValueDefault() {

    }

    /**
     * This method is used to set event for views
     */
    private void setEvent() {

    }

    /**
     * This method is used to set the callback event for header bar
     *
     * @param l
     */
    public void setOnHeaderBarListener(OnHeaderBarListener l) {
        this.mOnHeaderBarListener = l;
    }


    /**
     * This method is used to set title for header bar
     *
     * @param title is the title that you want to set for
     */
    public void setTitle(String title) {
        mTvTitleHeader.setText(title);
    }

    /**
     * This method is used to set the type for header bar. For each screen the
     * header bar is different
     *
     * @param type
     */
    public void setType(HeaderBarType type) {
        switch (type) {
            case TYPE_HOME:
                mTvTitleHeader.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {

    }


    public interface OnHeaderBarListener {

    }
}
