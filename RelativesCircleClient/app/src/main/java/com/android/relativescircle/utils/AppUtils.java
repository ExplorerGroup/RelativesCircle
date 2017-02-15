package com.android.relativescircle.utils;

import android.os.Build;

import java.util.Random;

/**
 * Created by Administrator on 2016/12/4 0004.
 */

public class AppUtils {


    public static String getUniqueID() {
        if (Build.VERSION.SDK_INT < 9) {
            return "451551315ad45d" + new Random().nextInt(1000);
        }
        return Build.SERIAL;
    }
}
