package com.android.relativescircle.commom;

import android.content.Context;
import android.support.annotation.Nullable;

import java.io.IOException;

/**
 * Created by Administrator on 2016/12/3 0003.
 */

public class FtServer {

    private static FtServer ftServer;
    private static OkHttpClientManager okHttpClientManager;
    Context mApplicationContext;

    private FtServer(Context context){
        mApplicationContext = context;
    }

    public  static FtServer getPPChatServerInstance(@Nullable  Context context){
        if(ftServer ==null){
            ftServer = new FtServer(context);
        }
        return ftServer;
    }

    public  String login(String encryptName,String encryptPwd,
                               String platform,String token,String userName,String location,String userType){
        OkHttpClientManager.Param param1 = new OkHttpClientManager.Param("encryptName",encryptName);
        OkHttpClientManager.Param param2 = new OkHttpClientManager.Param("encryptPwd",encryptPwd);
        OkHttpClientManager.Param param3 = new OkHttpClientManager.Param("platform",platform);
        OkHttpClientManager.Param param4 = new OkHttpClientManager.Param("token",token);
        OkHttpClientManager.Param param5 = new OkHttpClientManager.Param("location",location);
        OkHttpClientManager.Param param6 = new OkHttpClientManager.Param("userName",userName);
        OkHttpClientManager.Param param7 = new OkHttpClientManager.Param("userType",userType);
        try {
            return OkHttpClientManager.postSync(mApplicationContext, FT_URI.LOGIN,param1,param2,param3,param4,param5,param6,param7);
        } catch (IOException e) {
            return "";
        }
    }

    public  String register(String encryptName, String encryptPwd, String userName, String userType){
        OkHttpClientManager.Param param1 = new OkHttpClientManager.Param("encryptName",encryptName);
        OkHttpClientManager.Param param2 = new OkHttpClientManager.Param("encryptPwd",encryptPwd);
        OkHttpClientManager.Param param3 = new OkHttpClientManager.Param("userName",userName);
        OkHttpClientManager.Param param4 = new OkHttpClientManager.Param("userType",userType);
        try {
            return OkHttpClientManager.postSync(mApplicationContext, FT_URI.REGISTER,param1,param2,param3,param4);
        } catch (IOException e) {
            return "";
        }
    }

}
