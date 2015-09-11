package vn.asiantech.LearingEnglish.models;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tantv on 02/09/2015.
 */
public class SomeOtherFunction {

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
    public static ArrayList<Boolean> resultUser(ArrayList<QuestionData> mQuestionDataDatas,ArrayList<String> mListSelections) {
        ArrayList<Boolean> mIsResultUser = new ArrayList<Boolean>();
        for (int id = 0; id < mQuestionDataDatas.size(); id++) {
            String selection;
            switch (mQuestionDataDatas.get(id).getSelectionUser()) {
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
            Log.d("Ketqua " + id + ": ", mIsResultUser.get(id) + "");
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
}