package com.mn.pp.dao; 

import com.mn.pp.BasicDao;
import com.mn.pp.pojo.LoginSession;
import com.mn.pp.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface LoginSessionDao extends BasicDao {
    LoginSession findByUserId(@Param("userId")String userId);
}
