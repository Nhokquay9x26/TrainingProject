package vn.asiantech.LearingEnglish.models;

import com.google.gson.annotations.Expose;

/**
 *  Created by sunday on 11/09/2015.
 */
public class Register {

    @Expose
    private Boolean error;
    @Expose
    private String message;

    /**
     *
     * @return
     * The error
     */
    public Boolean getError() {
        return error;
    }

    /**
     *
     * @param error
     * The error
     */
    public void setError(Boolean error) {
        this.error = error;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}