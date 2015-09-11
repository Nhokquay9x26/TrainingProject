package vn.asiantech.LearingEnglish.models;

import lombok.Data;

/**
 * Created by Tran Quoc Hoan on 11/09/2015.
 */
@Data
public class ChangePassWord {

    /**
     * error : false
     * message : Password change successfully
     */

    private boolean error;
    private String message;

//    public void setError(boolean error) {
//        this.error = error;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public boolean getError() {
//        return error;
//    }
//
//    public String getMessage() {
//        return message;
//    }
}
