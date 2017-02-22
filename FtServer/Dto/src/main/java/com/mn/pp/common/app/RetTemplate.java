package com.mn.pp.common.app;

import com.mn.pp.core.utils.JackSonUtil;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/20 0020.
 * 返回給客戶端的模板类
 */
public final class RetTemplate implements Serializable {

    private int retCode; //返回码
    private Object retValue = ""; // 各种json

    public RetTemplate() {
    }


    public RetTemplate(int retCode, String retValue) {
        this.retCode = retCode;
        this.retValue = retValue;
    }

    public int getRetCode() {
        return retCode;
    }

    public RetTemplate setRetCode(int retCode) {
        this.retCode = retCode;
        return this;
    }

    public Object getRetValue() {
        return retValue;
    }

    public RetTemplate setRetValue(Object retValue) {
        this.retValue = retValue;
        return this;
    }

    public String toJson() {
        return JackSonUtil.obj2Json(this);
    }
}
