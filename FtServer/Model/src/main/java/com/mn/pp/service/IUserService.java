package com.mn.pp.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mn on 2016/11/23 0023.
 * <p/>
 * 所有用户有关的业务全部放在这里面
 * 比如 登录 注册 修改密码 修改用户信息等等
 */
public interface IUserService extends IBaseService {

    /**
     * 登录
     * @param encryptName 加密用户名
     * @param encryptPwd 加密密码
     * @param platform 登录平台
     * @param token    设备唯一id
     * @param userName 用户名MD5
     * @param userType 用户类型  qq,微信
     * @return 登录结果
     */
    String login(String encryptName,String encryptPwd,
                  String platform,String token,String userName,String userType);

    /**
     * 注册
     * @param encryptName 加密用户名
     * @param encryptPwd 加密密码
     * @param userName 用户名MD5
     * @param userType  默认,qq,微信
     * @return
     */
    String register(String encryptName, String encryptPwd, String userName, String userType);

    /**
     * 上传头像
     * @param sessionId
     * @param request
     * @param response
     * @return
     */
    String uploadHead(String sessionId,HttpServletRequest request, HttpServletResponse response);

    /**
     * 更新用户信息
     * @param sessionId
     * @param fieldName
     * @param fieldValue
     * @return
     */
    String updateUserDetail(String sessionId,String fieldName,Object fieldValue);

    /**
     * 获取用户详情信息
     * @param sessionId
     * @return
     */
    String getUserDetail(String sessionId);
}
