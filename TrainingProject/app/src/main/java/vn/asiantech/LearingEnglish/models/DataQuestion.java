package vn.asiantech.LearingEnglish.models;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by tantv on 28/08/2015.
 */

public class DataQuestion {
    static String dataQuestion[]=
            {"....all the candidates applying for the job, Mr.Wang appears to be the most promissing",
                    "The _____ limit for that activity was 10 minutes.",
                    "In this month's budget, the company has _____ $200,000 to the implementation of a new pension plan.",
                    "You dont like our teacher, do you ?",
                    " ...three weeks, we go to the movies."};
    static String questionA[] = {"On","time","negotiated","Yes, I hate his character","each"};
    static String questionB[] = {"between","timeless","challenged","No, I dont like his way of teaching","all"};
    static String questionC[] = {"through","times","distributed","yes , he is a bad teacher","every"};
    static String questionD[] = {"of","timely","allocated","No, He is wonderful","any"};
    public static void getDataQuestion(ArrayList<Question> questionData){
        Question question;
        for (int i=0;i<dataQuestion.length;i++){
            question = new Question(dataQuestion[i],questionA[i],questionB[i],questionC[i],questionD[i],0);
            questionData.add(question);
            Log.d("Size1", i + "");
        }

    }
}
