package com.mn.pp.common.app;

import com.mn.pp.core.utils.JackSonUtil;

/**
 * Created by Administrator on 2016/11/24 0024.
 * 常用返回模板 返回码
 */
public interface CommRetTemplate {
    /**
     * 返回码
     */
    interface RetCode {
        //操作成功
        int RETCODE_SUCCESS = 0x10;
        //用户相关
        int RETCODE_PASSWOER_ERR = 0x11;//密码错误
        int RETCODE_USER_NON_EXIST = 0x12;//用户不存在
        int RETCODE_USER_EXIST = 0x13;//用户已存在
        int RETCODE_LOGIN_SESSION_EXPIRED = 0x14;//会话过期
        int RETCODE_USER_TYPE_ERR = 0x15;//用户类型错误 不匹配
        //参数异常相关
        int RETCODE_PARAM_PARSE_ERR = 0x30; //参数格式不对 不能json解析,为空等
        int RETCODE_PATAM_TYPE_ERR = 0x31;//参数类型错误
        int RETCODE_UNKWON = 0x32;//未知错误


    }

    String TEMPLATE_SUCCESS =
            JackSonUtil.obj2Json(new RetTemplate(RetCode.RETCODE_SUCCESS, ""));

    String TEMPLATE_PASSWORD_ERR =
            JackSonUtil.obj2Json(new RetTemplate(RetCode.RETCODE_PASSWOER_ERR, ""));

    String TEMPLATE_USER_NON_EXIST =
            JackSonUtil.obj2Json(new RetTemplate(RetCode.RETCODE_USER_NON_EXIST, ""));

    String TEMPLATE_USER_EXIST =
            JackSonUtil.obj2Json(new RetTemplate(RetCode.RETCODE_USER_EXIST, ""));

    String TEMPLATE_LOGIN_SESSION_EXPIRED =
            JackSonUtil.obj2Json(new RetTemplate(RetCode.RETCODE_LOGIN_SESSION_EXPIRED, ""));

    String TEMPLATE_PARAM_ERR =
            JackSonUtil.obj2Json(new RetTemplate(RetCode.RETCODE_PARAM_PARSE_ERR, ""));

    String TEMPLATE_PATAM_TYPE_ERR =
            JackSonUtil.obj2Json(new RetTemplate(RetCode.RETCODE_PATAM_TYPE_ERR, ""));

    String TEMPLATE_USER_TYPE_ERR =
            JackSonUtil.obj2Json(new RetTemplate(RetCode.RETCODE_USER_TYPE_ERR, ""));

    String TEMPLATE_UNKWON =
            JackSonUtil.obj2Json(new RetTemplate(RetCode.RETCODE_UNKWON, ""));
}
