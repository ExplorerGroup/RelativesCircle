package com.mn.pp.common.app;

import com.mn.pp.pojo.Moment;
import com.mn.pp.pojo.MomentImage;
import com.mn.pp.pojo.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/26 0026.
 */
public class MomentRetBean {

    private String userId;
    private String userHeadUrl;
    private String nickName;
    private String userName;
    private String phoneNumber;
    private String momentId;
    private String momentContext;
    private List<String>  largeImageUrlList;
    private List<String>  normalImageUrlList;
    private List<String>  smallImageUrlList;
    private Date createOn;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserHeadUrl() {
        return userHeadUrl;
    }

    public void setUserHeadUrl(String userHeadUrl) {
        this.userHeadUrl = userHeadUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMomentId() {
        return momentId;
    }

    public void setMomentId(String momentId) {
        this.momentId = momentId;
    }

    public String getMomentContext() {
        return momentContext;
    }

    public void setMomentContext(String momentContext) {
        this.momentContext = momentContext;
    }

    public List<String> getLargeImageUrlList() {
        return largeImageUrlList;
    }

    public void setLargeImageUrlList(List<String> largeImageUrlList) {
        this.largeImageUrlList = largeImageUrlList;
    }

    public List<String> getNormalImageUrlList() {
        return normalImageUrlList;
    }

    public void setNormalImageUrlList(List<String> normalImageUrlList) {
        this.normalImageUrlList = normalImageUrlList;
    }

    public List<String> getSmallImageUrlList() {
        return smallImageUrlList;
    }

    public void setSmallImageUrlList(List<String> smallImageUrlList) {
        this.smallImageUrlList = smallImageUrlList;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public final static class  Builder{
        static Builder builder;

        public static Builder getInsBuilder(){
            if(builder==null){
                synchronized (Builder.class){
                    if (builder==null){
                        builder = new Builder();
                    }
                }
            }
            return builder;
        }

        public MomentRetBean build(User user,Moment moment,List<MomentImage> momentImageList){
            if(user==null||moment==null){
                return null;
            }
            MomentRetBean momentRetBean = new MomentRetBean();
            momentRetBean.userId = user.getUid();
            momentRetBean.nickName = user.getNickname();
            momentRetBean.phoneNumber = user.getPhoneNumber();
            momentRetBean.userName = user.getUsername();
            momentRetBean.momentId = moment.getUid();
            momentRetBean.momentContext = moment.getTextcontent();
            momentRetBean.createOn = moment.getCreateon();
            if(momentImageList!=null&&momentImageList.size()!=0){
                List<String> largeImageList = new ArrayList<>();
                List<String> normalImageList = new ArrayList<>();
                List<String> smallImageList = new ArrayList<>();
                for (MomentImage momentImage:momentImageList){
                    largeImageList.add(momentImage.getLargeimage());
                    normalImageList.add(momentImage.getNormalimage());
                    smallImageList.add(momentImage.getSmallimage());
                }
                momentRetBean.largeImageUrlList = largeImageList;
                momentRetBean.normalImageUrlList = normalImageList;
                momentRetBean.smallImageUrlList = smallImageList;
            }
           return  momentRetBean;
        }
    }
}
