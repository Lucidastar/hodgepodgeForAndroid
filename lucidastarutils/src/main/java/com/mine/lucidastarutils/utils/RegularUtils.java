package com.mine.lucidastarutils.utils;

import android.util.Log;

import com.mine.lucidastarutils.constant.RegexConstants;
import com.mine.lucidastarutils.log.KLog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by qiuyouzone on 2017/10/9.
 */

public final class RegularUtils {
    private RegularUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 验证邮箱
     * @param input  验证输入的文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isEmail(final CharSequence input){
        return isMatch(RegexConstants.REGEX_EMAIL, input);
    }

    /**
     * 判断是否匹配正则
     *
     * @param regex 正则表达式
     * @param input 要匹配的字符串
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isMatch(final String regex, final CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }
}
