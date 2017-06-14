package com.woch.wangqiwahahah.library;

/**
 * Created by wangqiWahahah on 2017/6/14.
 */

public class StringUtils {

    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

}
