package com.mn.pp.pojo;


import com.mn.pp.BasicVo;

public class MomentImage extends BasicVo {
    private static final long serialVersionUID = 1L;

    private String uid;
    private String mid;
    private String largeimage;
    private String normalimage;
    private String smallimage;
    private String description;


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

    public String getLargeimage() {
        return largeimage;
   }

    public void setLargeimage(String largeimage) {
        this.largeimage = largeimage;
   }

    public String getNormalimage() {
        return normalimage;
   }

    public void setNormalimage(String normalimage) {
        this.normalimage = normalimage;
   }

    public String getSmallimage() {
        return smallimage;
   }

    public void setSmallimage(String smallimage) {
        this.smallimage = smallimage;
   }

    public String getDescription() {
        return description;
   }

    public void setDescription(String description) {
        this.description = description;
   }
}

/*List columns as follows:
"uid", "mid", "largeimage", "normalimage", "smallimage", "description", "createon", 
"updateon", "isdelete"
*/