package vn.asiantech.LearingEnglish.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import vn.asiantech.LearingEnglish.R;


@EFragment(R.layout.fragment_setting)
public class SettingFragment extends BaseFragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Click(R.id.imgBtnNextTopSetting)
    void clickTopSetting() {
        replaceFragment(new TopPointFragment_());
    }

    @Click(R.id.imgBtnNextAleartSetting)
    void clickAleartSetting() {
             replaceFragment(new AleartFragment_());
    }

    @Click(R.id.imgBtnNextLogOutSetting)
    void clickLogOutSetting() {
        AlertDialog.Builder buider = new AlertDialog.Builder(getActivity());
        buider.setTitle(R.string.textView_text_log_out_setting);
        buider.setMessage(R.string.textView_text_close_app);
        buider.setIcon(R.drawable.ic_log_out);


        buider.setPositiveButton(R.string.text_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //getActivity().getFragmentManager().beginTransaction().remove().commit();
                getActivity().finish();
            }
        });
        buider.setNegativeButton(R.string.text_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        buider.create().show();

    }

}