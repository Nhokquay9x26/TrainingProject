package vn.asiantech.LearingEnglish.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by TienHN on 8/17/15.
 */
public final class JapaneseUtil {
    private JapaneseUtil() {

    }

    /**
     * Check hiragana character
     * @param c
     * @return
     */
    public static boolean isHiragana(char c) {
        return Character.UnicodeBlock.of(c) == Character.UnicodeBlock.HIRAGANA;
    }

    /**
     * Check katakana character
     * @param c
     * @return
     */
    public static boolean isKatakana(char c) {
        return Character.UnicodeBlock.of(c) == Character.UnicodeBlock.KATAKANA;
    }


    /**
     * Check kanji character
     * @param c
     * @return
     */
    public static boolean isKanji(char c){
        return Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS;
    }

    /**
     * Check japanese character
     * @param c
     * @return
     */
    public static boolean isJapaneseCharacter(char c) {
        Set<Character.UnicodeBlock> blocks = new HashSet<>();
        blocks.add(Character.UnicodeBlock.KATAKANA);
        return blocks.contains(Character.UnicodeBlock.of(c));
    }

    /**
     * Check a string contain all full-size characters
     * @param str
     * @return
     */
    public static boolean isFullSize(String str) {
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if ((c <= '\u007e') ||
                    (c == '\u00a5') ||
                    (c == '\u203e') ||
                    (c >= '\uff61' && c <= '\uff9f')
                    ) {
                return false;
            }
        }
        return true;
    }
}
