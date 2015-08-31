package vn.asiantech.LearingEnglish.network;

import java.util.HashMap;
import java.util.Map;

import vn.asiantech.LearingEnglish.models.Login;
import vn.asiantech.LearingEnglish.network.core.SessionStore;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by tientun on 7/2/15.
 */
public class MySession extends SessionStore<Login> {
    private static final String KEY = "test-key";
    static final String HEADER_AUTH = "Authorization";
    static final String AUTH_PREFIX = "Bearer ";

    @Override
    protected Map<String, String> saveSession(Login data) {
        Map<String, String> session = new HashMap<>();
//        session.put("key1", data.getToken());
        return session;
    }

    @Override
    protected Login restoreSession(Map<String, ?> savedSession) {
        Login login = new Login();
        try {
//            login.setToken((String) savedSession.get("key1"));
//            login.setEmail((String) savedSession.get("key2"));
//            login.setUser_id((String) savedSession.get("key3"));
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return login;
    }

    @Override
    protected Map<String, String> getHeader(Login data) {
        Map<String, String> header = new HashMap<>();
//        header.put(HEADER_AUTH, AUTH_PREFIX + data.getToken());
        return header;
    }

    @Override
    protected String getPrivateKey() {
        return KEY;
    }

    @Override
    public boolean checkAuthenticated(Login data) {
        try {
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
