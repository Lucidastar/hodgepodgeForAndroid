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
     *
     * @param input 验证输入的文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isEmail(final CharSequence input) {
        return isMatch(RegexConstants.REGEX_EMAIL, input);
    }

    /**
     * 验证手机号（简单）
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isPhoneNumSimple(final CharSequence input) {
        return isMatch(RegexConstants.REGEX_MOBILE_SIMPLE, input);
    }

    /**
     * 验证手机号（精确）
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isPhoneNumExact(final CharSequence input) {
        return isMatch(RegexConstants.REGEX_MOBILE_EXACT, input);
    }

    /**
     * 只包含数字
     * @param input
     * @return
     */
    public static boolean isOnlyFigure(final CharSequence input){
        return isMatch(RegexConstants.REGEX_ONLY_NUB,input);
    }

    /**
     * 只包含字母
     * @param input
     * @return
     */
    public static boolean isOnlyChar(final CharSequence input){
        return isMatch(RegexConstants.REGEX_ONLY_CHAR,input);
    }

    /**
     * 包含字母和数字
     * @param input
     * @return
     */
    public static boolean isCharAndFigure(final CharSequence input){
        return isMatch(RegexConstants.REGEX_CHAR_AND_NUM,input);
    }

    /**
     * 判断密码强度
     *
     * @param str
     * @return
     */
    public static int checkPasswordStrength(final CharSequence str) {
        int flag = 0;
        if (isOnlyFigure(str)){
            flag = 1;
        }else if (isOnlyChar(str)){
            flag = 2;
        }else if (isCharAndFigure(str)){
            flag = 3;
        }
        return flag;
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
