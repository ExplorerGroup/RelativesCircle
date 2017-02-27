package com.mn.pp.pojo;


import com.mn.pp.BasicVo;

public class Moment extends BasicVo {
    private static final long serialVersionUID = 1L;

    private String uid;
    private String userid;
    private String textcontent;

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

    public String getTextcontent() {
        return textcontent;
   }

    public void setTextcontent(String textcontent) {
        this.textcontent = textcontent;
   }
}

/*List columns as follows:
"uid", "userid", "textcontent", "createon", "isdelete"
*/