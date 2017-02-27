package com.mn.pp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/2/26 0026.
 */
public interface IFriendService {

    /**
     * 添加好友
     * @param sessionId
     * @param friendUserName
     * @return
     */
    String addFriend(String sessionId,String friendUserName);

    /**
     *  获取好友列表
     * @return
     */
    String getFriendList(String  sessionId);

    /**
     * 删除好友
     * @param sessionId
     * @return
     */
    String deleteFriend(String sessionId,String fid);

    /**
     *  获取朋友圈  这里不使用分页
     * @param sessionId
     * @return
     */
    String getMomentList(String sessionId);

    /**
     * 添加朋友圈
     * @param sessionId
     * @param textContext
     * @return
     */
    String addMoment(HttpServletRequest request, HttpServletResponse response,String sessionId,String textContext);
    /**
     * 刪除朋友圈
     * @param sessionId
     * @param momentId
     * @return
     */
    String deleteMoment(String sessionId,String momentId);
}
