package vn.asiantech.LearingEnglish.models;

import android.content.Context;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tantv on 02/09/2015.
 */
public class FunctionModel {

    public static void changeTextViewToTwoColor(TextView TV, String str1, String str2, int colorOne, int colorTwo) {
        Spannable word = new SpannableString(str1);
        word.setSpan(new ForegroundColorSpan(colorOne), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        TV.setText(word);
        Spannable wordTwo = new SpannableString(str2);
        wordTwo.setSpan(new ForegroundColorSpan(colorTwo), 0, wordTwo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        TV.append(wordTwo);
    }


    /**
     * Calculate return result for user
     */
    public static ArrayList<Boolean> resultUser(ArrayList<Question> mQuestionData,ArrayList<String> mListSelections) {
        ArrayList<Boolean> mIsResultUser = new ArrayList<Boolean>();
        for (int id = 0; id < mQuestionData.size(); id++) {
            String selection;
            switch (mQuestionData.get(id).getSelectionUser()) {
                case 1: {
                    selection = "A";
                    break;
                }
                case 2: {
                    selection = "B";
                    break;
                }
                case 3: {
                    selection = "C";
                    break;
                }
                case 4: {
                    selection = "D";
                    break;
                }
                default:
                    selection = "";
            }
            if (selection.equals(mListSelections.get(id))) {
                mIsResultUser.add(true);
            } else {
                mIsResultUser.add(false);
            }
        }
        return mIsResultUser;
    }
    public static int countResultsTrue(ArrayList<Boolean> resultUser){
        int dem =0;
        for (int i=0;i<resultUser.size();i++){
            if (resultUser.get(i)){
                dem ++;
            }
        }
        return dem;
    }

    public static String convertTimeToString(long time) {
        long min = (time / 1000) / 60;
        long sec = (time / 1000) % 60;
        if (min < 10 && (sec < 10)) {
            return ("0" + min + ":0" + sec);
        } else if (min < 10 && (sec >= 10)) {
            return ("0" + min + ":" + sec);
        } else if (min >= 10 && (sec < 10)) {
            return (min + ":0" + sec);
        } else {
            return (min + ":" + sec);
        }
    }
    public static ArrayList<Uri> getRingtones(Context context) {
        RingtoneManager ringtoneMgr = new RingtoneManager(context);
        ringtoneMgr.setType(RingtoneManager.TYPE_ALARM);
        Cursor alarmsCursor = ringtoneMgr.getCursor();
        int alarmsCount = alarmsCursor.getCount();
        if (alarmsCount == 0 && !alarmsCursor.moveToFirst()) {
            return null;
        }
        ArrayList<Uri> alarms = new ArrayList<>();
        while (!alarmsCursor.isAfterLast() && alarmsCursor.moveToNext()) {
            int currentPosition = alarmsCursor.getPosition();
            alarms.add(ringtoneMgr.getRingtoneUri(currentPosition));
        }
        alarmsCursor.close();
        return alarms;
    }
}
