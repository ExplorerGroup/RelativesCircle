package com.mn.pp.TestUnit;

import com.mn.pp.core.utils.JackSonUtil;
import com.mn.pp.core.utils.StringUtils;
import com.mn.pp.pojo.FamilyMember;
import org.junit.Test;

/**
 * Created by Administrator on 2017/2/7 0007.
 * 家庭成员dao层和service测试类
 */

public class FamilyServiceTest extends AbstractSpringWithJunitTestRunner {

//    @Autowired
//    @Qualifier("familyMemberDao")
//    IFamilyMemberDao iFamilyMemberDao;
//
//    @Autowired
//    @Qualifier("familyMemberService")
//    IFamilyMemberService iFamilyMemberService;


    @Test
    public void save() {
//        LoginRetBean loginRet = new LoginRetBean();
//        RetTemplate retTemplate = new RetTemplate();
//        retTemplate.setRetCode(CommRetTemplate.RetCode.RETCODE_SUCCESS);
//        retTemplate.setRetValue(loginRet);
//        String s = JackSonUtil.obj2Json(retTemplate);
//        RetTemplate retTemplate1 = JackSonUtil.json2Obj(s, RetTemplate.class);
//        String s2 = retTemplate1.getRetValue().toString();
//        System.out.println(s2);
//
//        Gson gson = new Gson();
//        LoginRetBean loginRetBean = gson.fromJson(s2, LoginRetBean.class);
//        System.out.println(loginRetBean.getAge());
        //LoginRetBean loginRet1 = JackSonUtil.json2Obj(s2, LoginRetBean.class);

        //System.out.println(loginRet1.toString());
        //System.out.println(JackSonUtil.obj2Json(retTemplate));

        FamilyMember familyMember = new FamilyMember();
        familyMember.setShowName("你好");
        familyMember.setUid(StringUtils.getId());
        familyMember.setUserid("");
        System.out.println(JackSonUtil.obj2Json(familyMember));

       // iFamilyMemberDao.save(familyMember);
        //iFamilyMemberService.createFamilyMember("456",JackSonUtil.obj2Json(familyMember));
    }

    @Override
    public void delete() {

    }

    @Test
    public void find() {//201702072109210000010290602844
//       List<FamilyMember> familyMemberList = iFamilyMemberDao.findAll();
//       FamilyMember familyMember = iFamilyMemberDao.findById(familyMemberList.get(0).getUid());
//       System.out.println(familyMember.getShowName());
    }

    @Test
    public void update() {
//        List<FamilyMember> familyMemberList = iFamilyMemberDao.findAll();
//        iFamilyMemberService.updateFamilyMember("456",familyMemberList.get(0).getUid(), FamilyMember.NAME, "mm");
//        FamilyMember familyMember = iFamilyMemberDao.findById(familyMemberList.get(0).getUid());
//        System.out.println(familyMember.getShowName());
    }

    @Test
    public void remove() {
//        List<FamilyMember> familyMemberList = iFamilyMemberDao.findAll();
//        iFamilyMemberService.deleteFamilyMember("456",familyMemberList.get(0).getUid());
    }

    @Test
    public void removeAll() {
//        List<FamilyMember> familyMemberList = iFamilyMemberDao.findAll();
//        for (FamilyMember familyMember:familyMemberList){
//            iFamilyMemberService.deleteFamilyMember("456",familyMember.getUid());
//        }
    }

    @Test
    public void findByUserId() {
//        String s=iFamilyMemberService.findFamilyMember("456",null);
//        System.out.println(s);
    }


}
