package vn.asiantech.LearingEnglish.models;

import lombok.Data;

/**
 * Created by tantv on 28/08/2015.
 */
@Data
public class Question {

    private String question;
    private String selectionA;
    private String selectionB;
    private String selectionC;
    private String selectionD;
    private int selectionUser = 0;

    public Question(String question, String selectionA, String selectionB, String selectionC, String selectionD, int selectionUser) {
        this.question = question;
        this.selectionA = selectionA;
        this.selectionB = selectionB;
        this.selectionC = selectionC;
        this.selectionD = selectionD;
        this.selectionUser = selectionUser;
    }

}
