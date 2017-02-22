package com.mn.pp.pojo;


import com.mn.pp.BasicVo;

public class EncryptedUser extends BasicVo {
    private static final long serialVersionUID = 1L;

    private String uid;
    private String userid;
    private String encryptedusername;
    private String encryptedpassword;


    public String getUid() {
        return uid;
   }

    public void setUid(String uid) {
        this.uid = uid;
   }

    public String getUserid() {
        return userid;
   }

    public void setUserid(String userid) {
        this.userid = userid;
   }

    public String getEncryptedusername() {
        return encryptedusername;
   }

    public void setEncryptedusername(String encryptedusername) {
        this.encryptedusername = encryptedusername;
   }

    public String getEncryptedpassword() {
        return encryptedpassword;
   }

    public void setEncryptedpassword(String encryptedpassword) {
        this.encryptedpassword = encryptedpassword;
   }

}

/*List columns as follows:
"uid", "userid", "encryptedusername", "encryptedpassword", "createon", "updateon", "isdelete"
*/