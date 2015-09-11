package vn.asiantech.LearingEnglish.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tantv on 28/08/2015.
 */

public class ApplicationData {
    static String dataQuestion[] =
            {"....all the candidates applying for the job, Mr.Wang appears to be the most promissing",
                    "The _____ limit for that activity was 10 minutes.",
                    "In this month's budget, the company has _____ $200,000 to the implementation of a new pension plan.",
                    "You dont like our teacher, do you ?",
                    " ...three weeks, we go to the movies.",
                    "most of the large american auto companies stll lag .... the giant manufacturers based in janpan ,europe , and korea ",
                    "advertisements for the knife claim that the steel used in its manufacturing makes it practically ........"};

    static String questionA[] = {"On", "time", "negotiated", "Yes, I hate his character", "each", "before", "ordinary "};
    static String questionB[] = {"between", "timeless", "challenged", "No, I dont like his way of teaching", "all", "from", "unbreakable"};
    static String questionC[] = {"through", "times", "distributed", "yes , he is a bad teacher", "every", "behind", "dynanic"};
    static String questionD[] = {"of", "timely", "allocated", "No, He is wonderful", "any", "under", "unnecessary"};
    static String selectionTrue[] = {"A", "C", "B", "D", "D", "C", "B"};

    public static void getDataQuestion(ArrayList<Question> questionData) {
        Question question;
        for (int i = 0; i < dataQuestion.length; i++) {
            question = new Question(dataQuestion[i], questionA[i], questionB[i], questionC[i], questionD[i], 0);
            questionData.add(question);
            Log.d("Size1", i + "");
        }
    }

    public static void getSelectionTrue(ArrayList<String> listSelection) {
        for (int i = 0; i < selectionTrue.length; i++) {
            listSelection.add(selectionTrue[i]);
        }
    }

    static int rankID[] = {1, 2, 3, 4, 5};
    static String urlImageAvatar[] = {"", "", "", "", ""};
    static String nameUser[] = {"Son Chicken", "Phu LX", "Hoang NL", "Hai VL", "LX Thuan"};
    static int pointUser[] = {100, 80, 40, 70, 20};

    public static ArrayList<Ranking> getRankUser() {
        ArrayList<Ranking> rankings = new ArrayList<Ranking>();
        Ranking ranking;
        for (int i = 0; i < rankID.length; i++) {
            ranking = new Ranking(rankID[i], urlImageAvatar[i], nameUser[i], pointUser[i]);
            rankings.add(ranking);
        }
        return rankings;
    }


    public static ArrayList<NumberTesting> getNumberTestings() {
        ArrayList<NumberTesting> numberTestings = new ArrayList<>();
        NumberTesting numberTesting;
        for (int i = 0; i < 19; i++) {
            numberTesting = new NumberTesting("Level " + (i + 1), "30'");
            numberTestings.add(numberTesting);
        }
        return numberTestings;
    }
}

