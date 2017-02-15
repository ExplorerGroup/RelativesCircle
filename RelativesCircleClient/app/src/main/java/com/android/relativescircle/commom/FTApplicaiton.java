package com.android.relativescircle.commom;

import android.app.Application;

/**
 * Created by Administrator on 2016/12/4 0004.
 */

public class FTApplicaiton extends Application{

    private static String sessionId;

    public static String getSessionId() {
        return sessionId;
    }

    public static void setSessionId(String sessionId) {
        FTApplicaiton.sessionId = sessionId;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
