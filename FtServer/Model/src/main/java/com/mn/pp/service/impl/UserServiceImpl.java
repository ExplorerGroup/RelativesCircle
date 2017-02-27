package com.mn.pp.service.impl;


import com.mn.pp.common.app.CommRetTemplate;
import com.mn.pp.common.app.LoginRetBean;
import com.mn.pp.common.app.RetTemplate;
import com.mn.pp.common.app.UserDetailRetBean;
import com.mn.pp.core.utils.StringUtils;
import com.mn.pp.dao.EncryptedUserDao;
import com.mn.pp.dao.LoginSessionDao;
import com.mn.pp.dao.UserDao;
import com.mn.pp.pojo.EncryptedUser;
import com.mn.pp.pojo.LoginSession;
import com.mn.pp.pojo.User;
import com.mn.pp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by mn on 2016/11/24 0024.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserDao userDao;
    @Autowired
    EncryptedUserDao encryptedUserDao;
    @Autowired
    LoginSessionDao loginSessionDao;


    @Override
    public String login(String encryptedUserName, String encryptedPassword, String platform, String token, String userName, String userType) {
        User user = userDao.findUserByUserName(userName);
        if (user == null) {//用户不存在
            return CommRetTemplate.TEMPLATE_USER_NON_EXIST;
        }
        if (!userType.equals(user.getUsertype())) {//用户类型错误
            return CommRetTemplate.TEMPLATE_USER_TYPE_ERR;
        }
        EncryptedUser encryptedUser = encryptedUserDao.authentica(encryptedUserName, encryptedPassword);
        if (encryptedUser == null) {//密码错误
            return CommRetTemplate.TEMPLATE_PASSWORD_ERR;
        }
        LoginSession loginSession = loginSessionDao.findByUserId(user.getUid());
        if (loginSession == null) {//从来没有登录过 创建一个新的会话
            loginSession = new LoginSession();
            loginSession.setSessionid(StringUtils.getUID46());
            loginSession.setUserid(user.getUid());
            loginSession.setToken(token);
            loginSession.setPlatform(platform);
            loginSessionDao.save(loginSession);
        }
        if (!loginSession.getToken().equals(token)) {
            //token不一致表示在另外一台手机上登录 这里需要将原来那个sessionId给过期掉并创建新的session
            loginSessionDao.delete(loginSession);
            loginSession.setToken(token);
            loginSession.setSessionid(StringUtils.getUID46());
            loginSession.setPlatform(platform);
        }
        return LoginRetBean.Builder.getInsBuilder().build(CommRetTemplate.RetCode.RETCODE_SUCCESS, loginSession, user);
    }

    @Override
    public String register(String encryptName, String encryptPwd, String userName, String userType) {
        User user = userDao.findUserByUserName(userName);
        if (user != null) {//该用户名已存在
            return CommRetTemplate.TEMPLATE_USER_EXIST;
        }
        user = new User();
        user.setUid(StringUtils.getUID46());
        user.setUsername(userName);
        user.setUsertype(userType);
        userDao.save(user);
        EncryptedUser encryptedUser = new EncryptedUser();
        encryptedUser.setUid(StringUtils.getUID46());
        encryptedUser.setUserid(user.getUid());
        encryptedUser.setEncryptedusername(encryptName);
        encryptedUser.setEncryptedpassword(encryptPwd);
        encryptedUserDao.save(encryptedUser);
        return CommRetTemplate.TEMPLATE_SUCCESS;
    }

    @Override
    public String uploadHead(String sessionId, HttpServletRequest request, HttpServletResponse response) {
        User user = userDao.findUserBySessionId(sessionId);
        if (user == null) {
            return CommRetTemplate.TEMPLATE_LOGIN_SESSION_EXPIRED;
        }
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (!multipartResolver.isMultipart(request)) {
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        //将request变成多部分request
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
            File folder = new File(request.getSession().getServletContext().getRealPath("/") + "user\\/"+ user.getUid());
            if (!folder.exists()) {
                folder.mkdirs();
            }
            String path = folder.getAbsolutePath() + "\\/" + file.getOriginalFilename();
            //上传
            try {
                file.transferTo(new File(path));
                String url = "Http://" + request.getServerName() + ":" + request.getLocalPort() + request.getContextPath() + "/userRestful" + "/userHeadPortrait?"
                        + "userId=" + user.getUid() + "&fileName=" + file.getOriginalFilename();
                user.setHeadurl(url);
                userDao.update(user);
                request.setAttribute("fileUrl", path);
                File[] fileArray = folder.listFiles();
                if (fileArray == null) {
                    continue;
                }
                for (File file1 : fileArray) {
                    if (!file1.getName().equals(file.getOriginalFilename()) && !file1.isDirectory()) {
                        file1.delete();
                    }
                }
                RetTemplate retTemplate = new RetTemplate();
                retTemplate.setRetCode(CommRetTemplate.RetCode.RETCODE_SUCCESS)
                        .setRetValue("{headUrl:" + url + "}");
                return retTemplate.toJson();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return CommRetTemplate.TEMPLATE_UNKWON;
    }

    @Override
    public String updateUserDetail(String sessionId, String fieldName, Object fieldValue) {
        User user = userDao.findUserBySessionId(sessionId);
        if (user == null) {
            return CommRetTemplate.TEMPLATE_LOGIN_SESSION_EXPIRED;
        }
        try {
            user.set(fieldName, fieldValue);
        } catch (Exception e) {
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        userDao.update(user);
        return CommRetTemplate.TEMPLATE_SUCCESS;
    }

    @Override
    public String getUserDetail(String sessionId) {
        User user = userDao.findUserBySessionId(sessionId);
        if (user == null) {
            return CommRetTemplate.TEMPLATE_LOGIN_SESSION_EXPIRED;
        }
        return UserDetailRetBean.Builder.getInsBuilder().build(CommRetTemplate.RetCode.RETCODE_SUCCESS, user);
    }
}
