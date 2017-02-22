package com.mn.pp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/2/7 0007.
 */

public interface IFamilyMemberService {
    /**
     * 修改家庭成員字段信息
     * @param _id 唯一id
     * @param fieldName 字段名
     * @param fieldValue 字段值
     * @return
     */
    String updateFamilyMember(String sessionId,String _id,String fieldName,Object fieldValue);
    /**
     * 创建家庭成员
     * @param familyMemberJson 家庭成员json
     * @return
     */
    String createFamilyMember(String sessionId,String familyMemberJson);

    /**
     * 删除家庭成员
     * @param _id 唯一id
     * @return
     */
    String deleteFamilyMember(String sessionId,String _id);

    /**
     *  获取用户的家庭圈 以及 家庭圈成员
     * @param sessionId
     * @return
     */
    String findFamilyMember(String sessionId);


    /**
     * 上传头像
     * @param sessionId
     * @param fmId
     * @param request
     * @param response
     * @return
     */
    String uploadHead(String sessionId,String fmId,HttpServletRequest request, HttpServletResponse response);

    /**
     * 创建家庭树
     * @param sessionId
     * @param showName
     * @return
     */
    String createGenealogy(String sessionId, String showName);

    /**
     * 查找家庭树下面所有的家庭成员
     * @param sessionId
     * @param gId 家庭成员Id
     * @return
     */
    String findFamilyMemberByGId(String sessionId, String gId);
}
