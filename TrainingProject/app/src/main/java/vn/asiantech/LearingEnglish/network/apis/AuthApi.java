package vn.asiantech.LearingEnglish.network.apis;

import retrofit.RetrofitError;
import vn.asiantech.LearingEnglish.models.Login;
import vn.asiantech.LearingEnglish.network.core.ApiClient;
import vn.asiantech.LearingEnglish.network.core.Callback;


/**
 * Copyright © 2015 AsianTech inc.
 * Created by tientun on 7/27/15.
 */
public class AuthApi {
    /**
     * Login to api
     *
     * @param email
     * @param password
     * @param callback
     */
    public static void login(String email, String password, final Callback<Login> callback) {
        ApiClient.call().login(email, password, new Callback<vn.asiantech.LearingEnglish.models.Login>() {
            @Override
            public void success(vn.asiantech.LearingEnglish.models.Login login) {
                callback.success(login);
            }

            @Override
            public void failure(RetrofitError error, vn.asiantech.LearingEnglish.network.Error myError) {
                callback.failure(error, myError);
            }
        });
    }
}
