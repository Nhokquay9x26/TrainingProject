package vn.asiantech.LearingEnglish.network;

import retrofit.RetrofitError;
import vn.asiantech.LearingEnglish.models.ForgotPass;
import vn.asiantech.LearingEnglish.models.InfoLogin;
import vn.asiantech.LearingEnglish.models.InfoRegister;
import vn.asiantech.LearingEnglish.network.core.ApiClient;
import vn.asiantech.LearingEnglish.network.core.Callback;

/**
 * Created by mrson on 10/09/2015.
 */
public class AuthorApi {
    public AuthorApi() {
    }

    //Function register new account
    public static void registerAccount(String email, String password, String name, final Callback<InfoRegister> callback) {
        ApiClient.call().register(email, password, name, new Callback<InfoRegister>() {
            @Override
            public void success(InfoRegister infoRegister) {
                callback.success(infoRegister);
            }

            @Override
            public void failure(RetrofitError error, Error myError) {
                callback.failure(error, myError);
            }
        });
    }

    //Function get new password
    public static void getNewPassWord(String mEmail, final Callback<ForgotPass> mForgotPassCallback) {
        ApiClient.call().forgotPassWord(mEmail, new Callback<ForgotPass>() {
            @Override
            public void success(ForgotPass forgotPass) {
                mForgotPassCallback.success(forgotPass);
            }

            @Override
            public void failure(RetrofitError error, Error myError) {
                mForgotPassCallback.failure(error, myError);
            }
        });
    }

    public static void loginAcount(String email, String password, final Callback<InfoLogin> callback) {
        ApiClient.call().login(email, password, new Callback<InfoLogin>() {
            @Override
            public void success(InfoLogin infoLogin) {
                callback.success(infoLogin);

            }

            @Override
            public void failure(RetrofitError error, Error myError) {
                callback.failure(error, myError);
            }
        });
    }

}