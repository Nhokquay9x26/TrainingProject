package vn.asiantech.LearingEnglish.adapter;


import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.models.SettingModel;

/**
 * @author mrson
 *         Created by mrson on 31/08/2015.
 */
public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.ViewHolder> {
    private final Context mContext;
    private List<SettingModel> mList = new ArrayList<>();


    public SettingAdapter(FragmentActivity mContextC, List<SettingModel> mList) {
        this.mContext = mContextC;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_recycleview_setting, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final SettingModel settingModel = mList.get(position);
        holder.tvNameProfileSetting.setText(settingModel.getProfilNameSetting());
        holder.imgProfile.setImageResource(settingModel.getProfileSetting());
        holder.tvNameProfileSetting.setText(settingModel.getProfilNameSetting());

        holder.imgProfile.setOnClickListener(v -> Toast.makeText(mContext, "click profile", Toast.LENGTH_SHORT).show());
        holder.imgSetting.setOnClickListener(v -> Toast.makeText(mContext, "click setting", Toast.LENGTH_SHORT).show());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public final vn.asiantech.LearingEnglish.utils.CircleImageView imgProfile;
        public final TextView tvNameProfileSetting;
        public final ImageView imgSetting;

        public ViewHolder(View itemView) {
            super(itemView);
            imgProfile = (vn.asiantech.LearingEnglish.utils.CircleImageView) itemView.findViewById(R.id.imgProfileSetting);
            tvNameProfileSetting = (TextView) itemView.findViewById(R.id.tvNameProfileSetting);
            imgSetting = (ImageView) itemView.findViewById(R.id.imgSettingProfile);

        }
    }

}
