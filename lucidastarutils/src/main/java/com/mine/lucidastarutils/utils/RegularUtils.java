package com.mine.lucidastarutils.utils;

import android.util.Log;

import com.mine.lucidastarutils.log.KLog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

/**
 * Created by qiuyouzone on 2017/10/9.
 */

public final class RegularUtils {
    private RegularUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isEmail(String email){
        boolean flag = false;
        try{
//            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
//            String check = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
            String check = "[\\\\w!#$%&'*+/=?^_`{|}~-]+(?:\\\\.[\\\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\\\w](?:[\\\\w-]*[\\\\w])?\\\\.)+[\\\\w](?:[\\\\w-]*[\\\\w])?";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }catch(Exception e){
            KLog.i("验证邮箱地址错误");
            flag = false;
        }
        return flag;
    }
}
