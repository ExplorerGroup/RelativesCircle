package com.mn.pp.pojo;

import com.mn.pp.BasicVo;

public class ManagementRole extends BasicVo {
    private static final long serialVersionUID = 1L;

    private String uid;
    private String showname;
    private Integer roletype;


    public String getUid() {
        return uid;
   }

    public void setUid(String uid) {
        this.uid = uid;
   }

    public String getShowname() {
        return showname;
   }

    public void setShowname(String showname) {
        this.showname = showname;
   }

    public Integer getRoletype() {
        return roletype;
   }

    public void setRoletype(Integer roletype) {
        this.roletype = roletype;
   }

}

/*List columns as follows:
"uid", "showname", "roletype", "createon", "updateon", "indelete"
*/