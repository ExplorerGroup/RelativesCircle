package com.mn.pp;  //改为你工程的相应package


import java.util.List;
import java.util.Map;

/**
 * mybatis DAO基类
 * <p/>
 * Created by HuQingmiao on 2015-5-29.
 */
public interface BasicDao {

    public int save(BasicVo basicVo);

    public int saveBatch(List list);

    public int update(BasicVo basicVo);

    public int updateIgnoreNull(BasicVo basicVo);

    public int updateBatch(List list);


    public int delete(BasicVo basicVo);

    public int deleteBatch(List list);

    public int deleteByPK(String id);

    public int deleteAll();


    public long count();

    public BasicVo findByPK(String id);

    public List find(Map<String, Object> paramMap);
}
