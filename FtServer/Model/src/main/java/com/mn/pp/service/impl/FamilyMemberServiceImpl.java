package com.mn.pp.service.impl;

import com.mn.pp.common.app.*;
import com.mn.pp.core.utils.JackSonUtil;
import com.mn.pp.core.utils.StringUtils;
import com.mn.pp.dao.FamilyMemberDao;
import com.mn.pp.dao.GenealogyDao;
import com.mn.pp.dao.LoginSessionDao;
import com.mn.pp.dao.UserDao;
import com.mn.pp.pojo.FamilyMember;
import com.mn.pp.pojo.Genealogy;
import com.mn.pp.pojo.LoginSession;
import com.mn.pp.pojo.User;
import com.mn.pp.service.IFamilyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by Administrator on 2017/2/7 0007.
 */
@Service
public class FamilyMemberServiceImpl implements IFamilyMemberService {
    @Autowired
    FamilyMemberDao familyMemberDao;
    @Autowired
    LoginSessionDao loginSessionDao;
    @Autowired
    UserDao userDao;


    @Autowired
    GenealogyDao genealogyDao;

    @Override
    public String updateFamilyMember(String sessionId, String fid, String fieldName, Object fieldValue) {
        LoginSession loginSession = (LoginSession) loginSessionDao.findByPK(sessionId);
        if (loginSession == null) {
            return CommRetTemplate.TEMPLATE_LOGIN_SESSION_EXPIRED;
        }
        FamilyMember familyMember = (FamilyMember) familyMemberDao.findByPK(fid);
        try {
            familyMember.set(fieldName, fieldValue);
        } catch (Exception e) {
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        familyMemberDao.update(familyMember);
        return CommRetTemplate.TEMPLATE_SUCCESS;
    }

    @Override
    public String createFamilyMember(String sessionId, String familyMemberJson) {
        LoginSession loginSession = (LoginSession) loginSessionDao.findByPK(sessionId);
        if (loginSession == null) {
            return CommRetTemplate.TEMPLATE_LOGIN_SESSION_EXPIRED;
        }
        FamilyMember familyMember = JackSonUtil.json2Obj(familyMemberJson, FamilyMember.class);
        if (familyMember == null) {
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        familyMember.setUid(StringUtils.getId());
        familyMember.setCreateon(new Date(System.currentTimeMillis()));
        familyMember.setIsdelete((byte) 1);
        familyMemberDao.save(familyMember);
        return CommRetTemplate.TEMPLATE_SUCCESS;
    }

    @Override
    public String deleteFamilyMember(String sessionId, String id) {
        LoginSession loginSession = (LoginSession) loginSessionDao.findByPK(sessionId);
        if (loginSession == null) {
            return CommRetTemplate.TEMPLATE_LOGIN_SESSION_EXPIRED;
        }
        FamilyMember familyMember = (FamilyMember) familyMemberDao.findByPK(id);
        if (familyMember == null) {
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        familyMemberDao.delete(familyMember);
        return CommRetTemplate.TEMPLATE_SUCCESS;
    }

    @Override
    public String findFamilyMember(String sessionId) {
        User user = userDao.findUserBySessionId(sessionId);
        if (user == null) {
            return CommRetTemplate.TEMPLATE_LOGIN_SESSION_EXPIRED;
        }
        Map<String, Object> argList = new HashMap<>();
        argList.put("adminId", user.getUid());
        List<Genealogy> genealogieList = genealogyDao.find(argList);
        if (genealogieList == null || genealogieList.size() == 0) {
            return CommRetTemplate.TEMPLATE_SUCCESS;
        }
        List<List<FamilyMember>> familyMemberListList = new ArrayList<>();
        for (Genealogy genealogy : genealogieList) {
            argList.clear();
            argList.put("gid", genealogy.getUid());
            List<FamilyMember> familyMemberList = familyMemberDao.find(argList);
            familyMemberListList.add(familyMemberList);
        }
        return FamilyMemberRet.Builder.getInsBuilder().build(CommRetTemplate.RetCode.RETCODE_SUCCESS, genealogieList, familyMemberListList);
    }

    @Override
    public String uploadHead(String sessionId, String fmId, HttpServletRequest request, HttpServletResponse response) {
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
            File folder = new File(request.getSession().getServletContext().getRealPath("/") + "familyMember\\/" + fmId);
            if (!folder.exists()) {
                folder.mkdir();

            }
            String path = folder.getAbsolutePath() + "/" + file.getOriginalFilename();
            //上传
            try {
                file.transferTo(new File(path));
                String url = "Http://" + request.getServerName() + ":" + request.getLocalPort() + request.getContextPath() +
                        "/" + "familyMemberRestful/headPortrait?"
                        + "fmId=" + fmId + "&fileName=" + file.getOriginalFilename();

                FamilyMember familyMember = new FamilyMember();
                familyMember.setHeadurl(url);
                familyMemberDao.update(familyMember);
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return CommRetTemplate.TEMPLATE_UNKWON;
    }

    @Override
    public String createGenealogy(String sessionId, String showName) {
        User user = userDao.findUserBySessionId(sessionId);
        if (user == null) {
            return CommRetTemplate.TEMPLATE_LOGIN_SESSION_EXPIRED;
        }
        Genealogy genealogy = new Genealogy();
        genealogy.setAminid(user.getUid());
        genealogy.setUid(StringUtils.getUID46());
        genealogy.setShowName(showName);
        genealogyDao.save(genealogy);
        RetTemplate retTemplate = new RetTemplate();
        retTemplate.setRetCode(CommRetTemplate.RetCode.RETCODE_SUCCESS)
                .setRetValue(GenealogyBean.Builder.getInsBuilder().build(genealogy, null));
        return retTemplate.toJson();
    }

    @Override
    public String findFamilyMemberByGId(String sessionId, String gId) {
        User user = userDao.findUserBySessionId(sessionId);
        if (user == null) {
            return CommRetTemplate.TEMPLATE_LOGIN_SESSION_EXPIRED;
        }
        Genealogy genealogy = (Genealogy) genealogyDao.findByPK(gId);
        if(genealogy==null){
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("gId",gId);
        List<FamilyMember> familyMembers = familyMemberDao.find(map);
        GenealogyBean genealogyBean = GenealogyBean
                .Builder.getInsBuilder().build(genealogy,
                        FamilyMemberRetBean.Builder.getInsBuilder().build(familyMembers));
        RetTemplate retTemplate = new RetTemplate();
        retTemplate.setRetCode(CommRetTemplate.RetCode.RETCODE_SUCCESS)
                .setRetValue(genealogyBean);
        return retTemplate.toJson();
    }


}
