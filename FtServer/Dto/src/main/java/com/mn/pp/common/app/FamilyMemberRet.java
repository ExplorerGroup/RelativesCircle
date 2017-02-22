package com.mn.pp.common.app;

import com.mn.pp.core.utils.JackSonUtil;
import com.mn.pp.pojo.FamilyMember;
import com.mn.pp.pojo.Genealogy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/7 0007.
 */
public final class FamilyMemberRet {

    String genealogyName;
    String familyMemberListJson;


    public FamilyMemberRet() {

    }

    public FamilyMemberRet(String familyMemberListJson, String genealogyName) {
        this.familyMemberListJson = familyMemberListJson;
        this.genealogyName = genealogyName;
    }


    /**
     * 构建返回json
     */
    public static final class Builder {
        private static Builder FamilyMemberRetBuilder;

        private Builder() {
        }

        public static Builder getInsBuilder() {
            if (FamilyMemberRetBuilder == null) {
                synchronized (Builder.class) {
                    if (FamilyMemberRetBuilder == null) {
                        FamilyMemberRetBuilder = new Builder();
                    }
                }
            }
            return FamilyMemberRetBuilder;
        }

        public String build(int retCode, List<Genealogy> genealogyList, List<List<FamilyMember>> familyMemberListList) {
            RetTemplate retTemplate = new RetTemplate();
            List<FamilyMemberRet> familyMemberRetList = convert(genealogyList, familyMemberListList);
            retTemplate.setRetCode(retCode);
            retTemplate.setRetValue(JackSonUtil.obj2Json(familyMemberRetList));
            return JackSonUtil.obj2Json(retTemplate);
        }

        public List<FamilyMemberRet> convert(List<Genealogy> genealogyList, List<List<FamilyMember>> familyMemberListList) {
            List<FamilyMemberRet> familyMemberRetList = new ArrayList<>();
            if (familyMemberListList == null) {
                return familyMemberRetList;
            }
            int size = genealogyList.size();
            for (int index = 0; index < size; index++) {
                String familyMemberListJson = JackSonUtil.obj2Json(familyMemberListList.get(index));
                familyMemberRetList.add(
                        new FamilyMemberRet(familyMemberListJson, genealogyList.get(index).getShowName()));
            }
            return familyMemberRetList;
        }
    }


}