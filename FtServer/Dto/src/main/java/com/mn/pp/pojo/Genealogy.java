package com.mn.pp.pojo;

import com.mn.pp.BasicVo;

public class Genealogy extends BasicVo {
    private static final long serialVersionUID = 1L;

    private String uid;
    private String aminid;
    private String showName;


    public String getUid() {
        return uid;
   }

    public void setUid(String uid) {
        this.uid = uid;
   }

    public String getAminid() {
        return aminid;
   }

    public void setAminid(String aminid) {
        this.aminid = aminid;
   }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }
}

/*List columns as follows:
"uid", "aminid", "createdon", "updateon", "isdelete"
*/