package com.android.relativescircle.commom;

/**
 * Created by Administrator on 2016/12/3 0003.
 */

public interface FT_URI {

    String PROTOCOL = "http://";
    String IP = "192.168.19.110";
    String PORT = ":8080";
    String SERVER_PATH = "/PPServer";
    String RESTFUL_USER = "/userRestful";

    String LOGIN = PROTOCOL + IP + PORT + SERVER_PATH + RESTFUL_USER + "/login";

    String REGISTER = PROTOCOL + IP + PORT + SERVER_PATH + RESTFUL_USER + "/register";
}
