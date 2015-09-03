package vn.asiantech.LearingEnglish.models;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

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

}
