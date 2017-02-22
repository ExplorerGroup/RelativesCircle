package com.mn.pp.common.app;

import com.mn.pp.pojo.Genealogy;

import java.util.List;

/**
 * Created by Administrator on 2017/2/20 0020.
 */
public final class GenealogyBean {

    private String uid;
    private String aminid;
    private String showName;
    List<FamilyMemberRetBean> familyMemberRetBeanList;

    public GenealogyBean(Genealogy genealogy, List<FamilyMemberRetBean> familyMemberRetBeanList) {
        this.uid = genealogy.getUid();
        this.aminid = genealogy.getAminid();
        this.showName = genealogy.getShowName();
        this.familyMemberRetBeanList = familyMemberRetBeanList;
    }

    public GenealogyBean() {
    }

    public static final class Builder{
        private static Builder builder;

        private Builder(){}

        public static Builder getInsBuilder(){
            if(builder==null){
                synchronized (Builder.class){
                    if(builder==null){
                        builder = new Builder();
                    }
                }
            }
            return builder;
        }

        public GenealogyBean build(Genealogy genealogy,List<FamilyMemberRetBean> familyMemberRetBeanList){
            return new GenealogyBean(genealogy,familyMemberRetBeanList);
        }
    }
}
