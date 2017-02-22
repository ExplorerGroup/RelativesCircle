package com.mn.pp.dao; 

import com.mn.pp.BasicDao;
import com.mn.pp.pojo.EncryptedUser;
import org.apache.ibatis.annotations.Param;

public interface EncryptedUserDao extends BasicDao {
    EncryptedUser authentica(@Param("encryptedUserName")String encryptedUserName, @Param("encryptedPassword") String encryptedPassword);
}
