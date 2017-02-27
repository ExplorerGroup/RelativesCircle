package com.mn.pp.service.impl;

import com.mn.pp.common.app.CommRetTemplate;
import com.mn.pp.common.app.MomentRetBean;
import com.mn.pp.common.app.RetTemplate;
import com.mn.pp.core.utils.StringUtils;
import com.mn.pp.dao.FriendsDao;
import com.mn.pp.dao.MomentDao;
import com.mn.pp.dao.MomentImageDao;
import com.mn.pp.dao.UserDao;
import com.mn.pp.pojo.*;
import com.mn.pp.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2017/2/26 0026.
 */

@Service(value = "friendServiceImpl")
public class FriendServiceImpl implements IFriendService {
    @Autowired
    MomentDao momentDao;
    @Autowired
    MomentImageDao momentImageDao;
    @Autowired
    UserDao userDao;
    @Autowired
    FriendsDao friendsDao;

    @Override
    public String addFriend(String sessionId, String friendUserName) {
        User user = userDao.findUserBySessionId(sessionId);
        if (user == null) {//用户不存在
            return CommRetTemplate.TEMPLATE_LOGIN_SESSION_EXPIRED;
        }
        try {
            User friendUser = userDao.findUserByUserName(friendUserName);
            Friends  friend1 = new Friends();
            friend1.setUid(StringUtils.getUID46());
            friend1.setFid(friendUser.getUid());
            friend1.setMid(user.getUid());

            Friends  friend2 = new Friends();
            friend2.setUid(StringUtils.getUID46());
            friend2.setFid(user.getUid());
            friend2.setMid(friendUser.getUid());
            friendsDao.save(friend1);
            friendsDao.save(friend2);
        } catch (Exception e) {
            return CommRetTemplate.TEMPLATE_UNKWON;
        }
        return CommRetTemplate.TEMPLATE_SUCCESS;
    }

    @Override
    public String getFriendList(String sessionId) {
        User user = userDao.findUserBySessionId(sessionId);
        if (user == null) {//用户不存在
            return CommRetTemplate.TEMPLATE_LOGIN_SESSION_EXPIRED;
        }
        Map<String,Object> keyMap = new HashMap<>();
        keyMap.put("mid", user.getUid());
        List<Friends> friendsList =  friendsDao.find(keyMap);
        keyMap.clear();
        if(friendsList==null||friendsList.size()==0){//暂无好友
            return CommRetTemplate.TEMPLATE_SUCCESS;
        }
        List<User> userList = new ArrayList<>();
        for (Friends friends:friendsList){
            User user1 = (User) userDao.findByPK(friends.getFid());
            userList.add(user1);
        }
        RetTemplate retTemplate = new RetTemplate();
        retTemplate.setRetCode(CommRetTemplate.RetCode.RETCODE_SUCCESS).setRetValue(userList);
        return retTemplate.toJson();
    }

    @Override
    public String deleteFriend(String sessionId, String fid) {
        User user = userDao.findUserBySessionId(sessionId);
        if (user == null) {//用户不存在
            return CommRetTemplate.TEMPLATE_LOGIN_SESSION_EXPIRED;
        }
        friendsDao.deleteByPK(fid);
        return CommRetTemplate.TEMPLATE_SUCCESS;
    }

    @Override
    public String getMomentList(String sessionId) {
        User user = userDao.findUserBySessionId(sessionId);
        if (user == null) {//用户不存在
            return CommRetTemplate.TEMPLATE_LOGIN_SESSION_EXPIRED;
        }
        Map<String,Object>  objectMap = new HashMap<>();
        objectMap.put("mid", user.getUid());
        List<Friends> friendsList = friendsDao.find(objectMap);//获取好友列表
        if(friendsList==null||friendsList.size()==0){
            return CommRetTemplate.TEMPLATE_SUCCESS;
        }
        objectMap.clear();
        List<MomentRetBean> momentRetBeanList = new ArrayList<>();
        for (Friends friends:friendsList){
            User user1 = (User) userDao.findByPK(friends.getFid());
            objectMap.clear();
            objectMap.put("userid",friends.getFid());
            List<Moment> momentList = momentDao.find(objectMap);//获取该用户的动态
            if(momentList==null||momentList.size()==0){
                continue;
            }
            List<MomentImage> momentImageList = null;
            for (Moment moment:momentList){
                objectMap.clear();
                objectMap.put("mid", moment.getUid());
                momentImageList = momentImageDao.find(objectMap);//获取动态的图片列表
                momentRetBeanList.add(MomentRetBean.Builder.getInsBuilder().build(user1, moment, momentImageList));
            }
        }
        RetTemplate retTemplate = new RetTemplate();
        retTemplate.setRetCode(CommRetTemplate.RetCode.RETCODE_SUCCESS).setRetValue(momentRetBeanList);
        return retTemplate.toJson();
    }

    @Override
    public String addMoment(HttpServletRequest request, HttpServletResponse response, String sessionId, String textContext) {
        User user = userDao.findUserBySessionId(sessionId);
        if (user == null) {//用户不存在
            return CommRetTemplate.TEMPLATE_LOGIN_SESSION_EXPIRED;
        }
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        Moment moment = new Moment();
        moment.setUid(StringUtils.getUID46());
        moment.setTextcontent(textContext);
        moment.setUserid(user.getUid());
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator it = multiRequest.getFileNames();
            while (it.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(it.next().toString());
                if (file == null) {
                    continue;
                }
                request.getSession().getServletContext().getRealPath("");
                //SysConfig._headPortraitPath
                File folder = new File(request.getSession().getServletContext().getRealPath("/")+ "moment\\/" + moment.getUid());
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                String path = folder.getAbsolutePath() + "\\/" + file.getOriginalFilename();
                //上传
                try {
                    file.transferTo(new File(path));
                    String url = "Http://" + request.getServerName() + ":" + request.getLocalPort() + request.getContextPath() +
                            "/" + "friends/moment/image?"
                            + "momentId=" + moment.getUid() + "&fileName=" + file.getOriginalFilename();

                    MomentImage momentImage = new MomentImage();
                    momentImage.setUid(StringUtils.getUID46());
                    momentImage.setMid(moment.getUid());
                    momentImage.setLargeimage(url);
                    momentImage.setNormalimage(url);
                    momentImage.setSmallimage(url);
                    momentImage.setDescription("");
                    momentImageDao.save(momentImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        momentDao.save(moment);
        return CommRetTemplate.TEMPLATE_SUCCESS;
    }

    @Override
    public String deleteMoment(String sessionId, String momentId) {
        User user = userDao.findUserBySessionId(sessionId);
        if (user == null) {//用户不存在
            return CommRetTemplate.TEMPLATE_LOGIN_SESSION_EXPIRED;
        }
        momentDao.deleteByPK(momentId);
        return CommRetTemplate.TEMPLATE_SUCCESS;
    }
}
