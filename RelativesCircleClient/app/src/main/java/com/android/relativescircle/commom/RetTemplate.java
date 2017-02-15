package com.android.relativescircle.commom;


import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/20 0020.
 * 模板类
 */
public class RetTemplate implements Serializable {

    private int retCode; //返回码
    private String retValue = ""; // 各种json

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

    public String getRetValue() {
        return retValue;
    }

    public RetTemplate setRetValue(String retValue) {
        this.retValue = retValue;
        return this;
    }
}
