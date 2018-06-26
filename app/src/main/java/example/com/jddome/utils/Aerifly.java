package example.com.jddome.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class Aerifly {
    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_TEL = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";


    //手机号验证
    public static boolean isMobile(Context context, String tel) {
        if (TextUtils.isEmpty(tel)) {
            Toast.makeText(context, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else if (tel.matches(REGEX_TEL)) {

        } else {
            Toast.makeText(context, "手机号不合法", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //密码验证
    public static boolean isPassword(Context context, String pwd) {
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else if (pwd.matches(REGEX_PASSWORD)) {

        } else {
            Toast.makeText(context, "密码必须在6-20位并且不包含特殊字符", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
