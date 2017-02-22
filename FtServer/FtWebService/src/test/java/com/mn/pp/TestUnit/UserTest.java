package com.mn.pp.TestUnit;

import com.mn.pp.BasicVo;
import com.mn.pp.core.utils.StringUtils;
import com.mn.pp.dao.FamilyMemberDao;
import com.mn.pp.dao.UserDao;
import com.mn.pp.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

/**
 * Created by Administrator on 2016/11/19 0019.
 */
public class UserTest extends AbstractSpringWithJunitTestRunner {

    @Autowired
    UserDao userDao;

    @Autowired
    FamilyMemberDao familyMemberDao;

    @Test
    public void saveUser() {
//        User user = new User();
//        user.setUpdateon(new Date(System.currentTimeMillis()));
//        user.setUid(StringUtils.getUID46());
//        user.setAge(12);
//        user.setCreateon(new Date(System.currentTimeMillis()));
//        user.setGender((byte) 1);
//        user.setHeadurl("ss");
//        user.setIsdelete((byte) 1);
//        user.setLocation("ss");
//        user.setNickname("ss");
//        user.setPersonalizedsignature("");
//        user.setPhoneNumber("ss");
//        user.setUsername("ss");
//        user.setUsertype("ss");
//        userDao.save(user);
        BasicVo basicVo = familyMemberDao.findByPK("1");
        System.out.println("");

    }

    @Test
    public void finaAll() {


    }

    @Test
    public void test() {

    }

    @Override
    public void find() {

    }

    @Override
    public void update() {

    }

    @Override
    public void save() {

    }

    @Override
    public void delete() {

    }
}
