package vn.asiantech.LearingEnglish.models;

import lombok.Data;

/**
 * Created by Tran Quoc Hoan on 11/09/2015.
 */
@Data
public class ForgotPass {

    /**
     * error : false
     * email : thaison@gmail.com
     * password : 123456
     */

    private boolean error;
    private String email;
    private String password;

    public void setError(boolean error) {
        this.error = error;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getError() {
        return error;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
