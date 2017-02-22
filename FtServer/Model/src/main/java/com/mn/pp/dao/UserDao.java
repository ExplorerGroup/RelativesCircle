package com.mn.pp.dao;


import com.mn.pp.BasicDao;
import com.mn.pp.pojo.User;

public interface UserDao extends BasicDao {


    User findUserByUserName(String userName);

    boolean userNameIsExist(String userName);

    User findUserBySessionId(String sessionId);

    void updateField(String uid, String fieldName, Object fieldValue);
}
