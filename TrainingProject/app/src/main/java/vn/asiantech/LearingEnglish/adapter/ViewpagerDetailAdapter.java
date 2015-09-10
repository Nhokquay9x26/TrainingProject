package vn.asiantech.LearingEnglish.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.models.AnimalCategory;

/**
 * @ Created by xuanphu on 05/09/2015.
 */
public class ViewpagerDetailAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<AnimalCategory> mArraylists = new ArrayList<>();
    private LayoutInflater inflater;
    private ImageView imgLeft;
    private ImageView imgRight;
    private CallBackNext mCallBackNext;

    public ViewpagerDetailAdapter(Context mContext, ArrayList<AnimalCategory> mArraylists, CallBackNext mCallBackNext) {
        this.mContext = mContext;
        this.mArraylists = mArraylists;
        this.mCallBackNext = mCallBackNext;
    }

    @Override
    public int getCount() {
        return mArraylists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imgAvataDetail;
        TextView tvNameDetail;
        inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_fragment_detail, container,
                false);
        imgAvataDetail = (ImageView) itemView.findViewById(R.id.imgAvataDetail);
        tvNameDetail = (TextView) itemView.findViewById(R.id.tvNameDetail);

        imgLeft = (ImageView) itemView.findViewById(R.id.imgSelecLeftDetail);
        imgRight = (ImageView) itemView.findViewById(R.id.imgSelecRightDetail);
        imgLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBackNext.OnClickBack();
            }
        });
        imgRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBackNext.OnClickNext();
            }
        });

        imgAvataDetail.setImageResource(mArraylists.get(position).getMAvataAnimal());
        tvNameDetail.setText(mArraylists.get(position).getMNameAnimal());

        ((ViewPager) container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }

    public interface CallBackNext {
        void OnClickNext();

        void OnClickBack();
    }
}
