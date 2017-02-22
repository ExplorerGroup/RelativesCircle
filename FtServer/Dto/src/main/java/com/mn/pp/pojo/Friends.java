package com.mn.pp.pojo;

import com.mn.pp.BasicVo;

public class Friends extends BasicVo {
    private static final long serialVersionUID = 1L;

    private String uid;
    private String mid;
    private String fid;

    public String getUid() {
        return uid;
   }

    public void setUid(String uid) {
        this.uid = uid;
   }

    public String getMid() {
        return mid;
   }

    public void setMid(String mid) {
        this.mid = mid;
   }

    public String getFid() {
        return fid;
   }

    public void setFid(String fid) {
        this.fid = fid;
   }


}

/*List columns as follows:
"uid", "mid", "fid", "createon", "isdelete", "updateon"
*/