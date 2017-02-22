package com.mn.pp.common.request;

/**
 * Created by Administrator on 2016/11/20 0020.
 * 客戶端登录需要的参数
 */
public class LoginUserInfo {
    private String location; // 登录地点
    private String encryptName; //加密的用户名
    private String encryptPwd;  //加密的密码
    private String platform;//Android��Web��IOS
    private String token; //设备的唯一id 比如IOS uuid
    private String userName; //单个用户名的加密 比如我的手机号码 12345566342
    // 将它md5就得到了userName; 在我的登录流程中是不需要将密码传上来的，encryptName encryptPwd是通过特殊的加密方式得到的 
    //由于是我师傅的专利,这里不便于公开


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEncryptName() {
        return encryptName;
    }

    public void setEncryptName(String encryptName) {
        this.encryptName = encryptName;
    }

    public String getEncryptPwd() {
        return encryptPwd;
    }

    public void setEncryptPwd(String encryptPwd) {
        this.encryptPwd = encryptPwd;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
