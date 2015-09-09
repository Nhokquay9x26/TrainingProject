package vn.asiantech.LearingEnglish.eventbus;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.activities.MainActivity_;


public class AleartReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        createNotification(context,"Time Up", "You should study English right now", "Learning English");
    }

    public void createNotification(Context context, String msg, String msgText, String msgAleart) {
        PendingIntent notifiIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity_.class), 0);

        NotificationCompat.Builder buidler = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_favorite_focus)
                .setContentTitle(msg)
                .setTicker(msgAleart)
                .setContentText(msgText);

        buidler.setContentIntent(notifiIntent);
        buidler.setDefaults(NotificationCompat.DEFAULT_SOUND);
        buidler.setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, buidler.build());
    }
}
