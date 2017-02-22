package com.mn.pp.pojo;

import com.mn.pp.BasicVo;

public class LoginSession extends BasicVo {
    private static final long serialVersionUID = 1L;

    private String sessionid;
    private String userid;
    private String token;
    private String platform;


    public String getSessionid() {
        return sessionid;
   }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
   }

    public String getUserid() {
        return userid;
   }

    public void setUserid(String userid) {
        this.userid = userid;
   }

    public String getToken() {
        return token;
   }

    public void setToken(String token) {
        this.token = token;
   }

    public String getPlatform() {
        return platform;
   }

    public void setPlatform(String platform) {
        this.platform = platform;
   }

}

/*List columns as follows:
"sessionid", "userid", "token", "platform", "pushid"
*/