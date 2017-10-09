package com.mine.lucidastarutils.constant;

/**
 * Created by qiuyouzone on 2017/10/9.
 */

public final class RegexConstants {

    /**
     * 正则：邮箱
     */
    public static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";


    /**
     * 正则：手机号（简单）
     */
    public static final String REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$";
    /**
     * 正则：手机号（精确）
     * <p>移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188</p>
     * <p>联通：130、131、132、145、155、156、171、175、176、185、186</p>
     * <p>电信：133、153、173、177、180、181、189</p>
     * <p>全球星：1349</p>
     * <p>虚拟运营商：170</p>
     */
    public static final String REGEX_MOBILE_EXACT  = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,1,3,5-8])|(18[0-9])|(147))\\d{8}$";

    /**
     * 只包含数字
     */
    public static final String REGEX_ONLY_NUB = "^\\d+$";

    /**
     * 只包含字母
     */
    public static final String REGEX_ONLY_CHAR = "^[a-zA-Z]+$";

    /**
     * 包含数字和字母
     */
    public static final String REGEX_CHAR_AND_NUM = "(?i)^((\\\\d+[\\\\da-z]*[a-z]+)|([a-z]+[\\\\da-z]*\\\\d+)|([a-z]+[\\\\da-z]*[a-z]*)|(\\\\d+[\\\\da-z]*\\\\d*))$";
}
