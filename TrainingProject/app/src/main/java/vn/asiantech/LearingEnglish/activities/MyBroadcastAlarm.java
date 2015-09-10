package vn.asiantech.LearingEnglish.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

/**
 * Created by mrson on 08/09/2015.
 */
@SuppressWarnings("DefaultFileTemplate")
public class MyBroadcastAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent != null) {
            if (intent.getBooleanExtra("isOff", true)) {
                Log.d("vvv", "turn of");
            } else {
                Log.d("vvv", "turn on");
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                final MediaPlayer player = MediaPlayer.create(context, notification);
                player.setLooping(true);
                player.start();
            }
        }
    }
}
