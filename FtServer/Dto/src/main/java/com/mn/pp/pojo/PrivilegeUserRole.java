package com.mn.pp.pojo;

import com.mn.pp.BasicVo;

public class PrivilegeUserRole extends BasicVo {
    private static final long serialVersionUID = 1L;

    private String uid;
    private String roleid;
    private String userid;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}

/*List columns as follows:
"uid", "roleid", "userid", "createon", "updateon", "isdelete"
*/