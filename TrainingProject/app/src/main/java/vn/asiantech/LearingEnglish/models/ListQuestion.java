package vn.asiantech.LearingEnglish.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ListQuestion {

    @Expose
    private Boolean error;
    @Expose
    private List<Question> question = new ArrayList<>();

    /**
     * @return The error
     */
    public Boolean getError() {
        return error;
    }

    /**
     * @param error The error
     */
    public void setError(Boolean error) {
        this.error = error;
    }

    /**
     * @return The question
     */
    public List<Question> getQuestion() {
        return question;
    }

    /**
     * @param question The question
     */
    public void setQuestion(List<Question> question) {
        this.question = question;
    }

}

class Question {

    @Expose
    private Integer id;
    @Expose
    private String detail;
    @SerializedName("answer_a")
    @Expose
    private String answerA;
    @SerializedName("answer_b")
    @Expose
    private String answerB;
    @SerializedName("answer_c")
    @Expose
    private String answerC;
    @SerializedName("answer_d")
    @Expose
    private String answerD;
    @Expose
    private String correct;

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail The detail
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @return The answerA
     */
    public String getAnswerA() {
        return answerA;
    }

    /**
     * @param answerA The answer_a
     */
    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    /**
     * @return The answerB
     */
    public String getAnswerB() {
        return answerB;
    }

    /**
     * @param answerB The answer_b
     */
    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    /**
     * @return The answerC
     */
    public String getAnswerC() {
        return answerC;
    }

    /**
     * @param answerC The answer_c
     */
    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    /**
     * @return The answerD
     */
    public String getAnswerD() {
        return answerD;
    }

    /**
     * @param answerD The answer_d
     */
    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    /**
     * @return The correct
     */
    public String getCorrect() {
        return correct;
    }

    /**
     * @param correct The correct
     */
    public void setCorrect(String correct) {
        this.correct = correct;
    }

}
