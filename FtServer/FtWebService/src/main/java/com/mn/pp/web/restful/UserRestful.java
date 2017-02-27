package com.mn.pp.web.restful;

import com.mn.pp.common.app.CommRetTemplate;
import com.mn.pp.core.utils.StringUtils;
import com.mn.pp.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by mn on 2016/11/20 0020.
 */

@Api(description = "用戶相关接口")
@Controller
@RequestMapping("/userRestful")
public final class UserRestful {

    @Autowired
    IUserService iUserService;


    @ApiOperation(value = "登录", notes = "登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/login", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    @ResponseBody
    public String login(
            @ApiParam(value = "加密用户名", required = true) @RequestParam String encryptName,
            @ApiParam(value = "加密密码", required = true) @RequestParam String encryptPwd,
            @ApiParam(value = "平台信息（Android|IOS)", required = true) @RequestParam String platform,
            @ApiParam(value = "设备唯一标示", required = true) @RequestParam String token,
            @ApiParam(value = "用户名", required = true) @RequestParam String userName,
            @ApiParam(value = "用户类型", required = true) @RequestParam String userType) {
        if (StringUtils.isNullorWhiteSpace(encryptName, encryptPwd, platform, token, userName, userType)) {//空字符串返回参数错误的模板
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        return iUserService.login(encryptName, encryptPwd, platform, token, userName, userType);
    }

    /**
     * 注册
     *
     * @param encryptName 加密用户名
     * @param encryptPwd  加密密码
     * @param userName    用户名
     * @param userType    用户类型
     * @return 注册结果
     */
    @ApiOperation(value = "注册", notes = "注册", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/register", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    @ResponseBody
    public String register(
            @ApiParam(value = "加密用户名", required = true) @RequestParam String encryptName,
            @ApiParam(value = "加密密码", required = true) @RequestParam String encryptPwd,
            @ApiParam(value = "用户名", required = true) @RequestParam String userName,
            @ApiParam(value = "用户类型", required = true) @RequestParam String userType) {
        if (StringUtils.isNullorWhiteSpace(encryptName, encryptPwd, userName, userType)) {//空字符串返回参数错误的模板
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        return iUserService.register(encryptName, encryptPwd, userName, userType);
    }


    @ApiOperation(value = "上传用户图像", notes = "需要带上图片資源", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/uploadUserPortrait")
    @ResponseBody
    public String uploadUserPortrait(
            @ApiParam(value = "会话id", required = true) @RequestParam String sessionId,
            HttpServletRequest request,
            HttpServletResponse response) {
        if (StringUtils.isNullorWhiteSpace(sessionId)) {
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        return iUserService.uploadHead(sessionId, request, response);
    }


    @ApiOperation(value = "获取用户头像", notes = "获取用户头像", httpMethod = "GET", produces = MediaType.IMAGE_GIF_VALUE)
    @RequestMapping(value = "/userHeadPortrait", produces = {MediaType.IMAGE_GIF_VALUE}, method = RequestMethod.GET)
    public void userHeadPortrait(HttpServletRequest request, HttpServletResponse response,
                                 @ApiParam(value = "用户id", required = true) @RequestParam String userId,
                                 @ApiParam(value = "文件名", required = true) @RequestParam String fileName) {
        if (StringUtils.isNullorWhiteSpace(userId, fileName)) {
            return;
        }
        String path = request.getSession().getServletContext().getRealPath("/") + "user\\/"+userId + "\\/" + fileName;
        FileInputStream fis = null;
        response.setContentType(MediaType.IMAGE_GIF_VALUE);
        try {
            OutputStream out = response.getOutputStream();
            File file = new File(path);
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                }
            }
        }
    }

    @ApiOperation(value = "修改個人信息", notes = "修改個人信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/updateUserDetail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public String updateUserDetail(
            @ApiParam(value = "会话id", required = true) @RequestParam String sessionId,
            @ApiParam(value = "字段名称", required = true/*,example = "age"*/) @RequestParam String fieldName,
            @ApiParam(value = "字段值", required = true) @RequestParam Object fieldValue) {
        if (StringUtils.isNullorWhiteSpace(sessionId)) {
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        return iUserService.updateUserDetail(sessionId, fieldName, fieldValue);
    }


    @ApiOperation(value = "获取用户信息", notes = "获取用户信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/userDetail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public String userDetail(@ApiParam(value = "会话id", required = true) @RequestParam String sessionId) {
        if (StringUtils.isNullorWhiteSpace(sessionId)) {
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        return iUserService.getUserDetail(sessionId);
    }
}
