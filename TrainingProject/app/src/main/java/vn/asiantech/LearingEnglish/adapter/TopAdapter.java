package vn.asiantech.LearingEnglish.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.container.TabOneContainer;
import vn.asiantech.LearingEnglish.core.fragments.Fragment;
import vn.asiantech.LearingEnglish.fragments.WordDetailContainerFragment_;
import vn.asiantech.LearingEnglish.models.Top;
import vn.asiantech.LearingEnglish.views.CircleImageView;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.TopViewHolder> {

    private Fragment mFragment;
    private ArrayList<Top> mListTop;

    public TopAdapter(Fragment mFragment, ArrayList<Top> mListTop) {
        this.mFragment = mFragment;
        this.mListTop = mListTop;
    }

    @Override
    public TopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mFragment.getActivity()).inflate(R.layout.item_list_top, parent, false);
        return new TopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopViewHolder holder, int position) {
        holder.mImgCategory.setImageResource(mListTop.get(position).getImgCategory());
        holder.mTxtCategory.setText(mListTop.get(position).getCategory());
    }

    @Override

    public int getItemCount() {
        return mListTop.size();
    }

    public class TopViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView mImgCategory;
        private TextView mTxtCategory;
        private ImageView mImgDetail;

        public TopViewHolder(View itemView) {
            super(itemView);
            mImgCategory = (CircleImageView) itemView.findViewById(R.id.imgCategory);
            mTxtCategory = (TextView) itemView.findViewById(R.id.txtCategory);
            mImgDetail = (ImageView) itemView.findViewById(R.id.imgDetail);
            mImgDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    android.support.v4.app.Fragment fragmentParent = mFragment.getParentFragment();
                    if (fragmentParent instanceof TabOneContainer) {
                        ((TabOneContainer) fragmentParent).replaceFragment(new WordDetailContainerFragment_(), false);
                    }

                }
            });
        }
    }
}
