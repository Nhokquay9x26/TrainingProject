package vn.asiantech.LearingEnglish.utils;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by TienHN on 3/10/15.
 */
public final class HashUtil {

    private HashUtil() {

    }

    /**
     * Encrypt string to MD5
     * @param text input string
     * @return encrypted string
     */
    public static String md5(String text) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(text.getBytes());
            byte[] a = digest.digest();
            int len = a.length;
            StringBuilder sb = new StringBuilder(len << 1);
            for (int i = 0; i < len; i++) {
                sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
                sb.append(Character.forDigit(a[i] & 0x0f, 16));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            Log.d("---", e.getMessage());
        }
        return null;
    }
}
