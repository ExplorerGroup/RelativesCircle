package com.mn.pp.web.restful;

import com.mn.pp.common.app.CommRetTemplate;
import com.mn.pp.core.utils.StringUtils;
import com.mn.pp.service.IFriendService;
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
 * Created by Administrator on 2017/2/26 0026.
 */


@Api(description = "朋友圈相关功能")
@RequestMapping("/friends")
@Controller
public class FriendRestful {
    @Autowired
    IFriendService friendService;

    @ApiOperation(value = "添加好友", notes = "添加好友", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/addFriend", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    @ResponseBody
    public String addFriend(
            @ApiParam(value = "登录时获取的sessionId", required = true) @RequestParam String sessionId,
            @ApiParam(value = "好友用户名，即登录时用的用户名", required = true) @RequestParam String firendUserName) {
        if (StringUtils.isNullorWhiteSpace(sessionId, firendUserName)) {
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        return friendService.addFriend(sessionId, firendUserName);
    }

    @ApiOperation(value = "获取好友列表", notes = "获取好友列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/friendList", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.GET)
    @ResponseBody
    public String friendList(@ApiParam(value = "登录时获取的sessionId", required = true) @RequestParam String sessionId) {
        if (StringUtils.isNullorWhiteSpace(sessionId)) {
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        return friendService.getFriendList(sessionId);
    }

//    @ApiOperation(value = "删除好友", notes = "删除好友", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @RequestMapping(value = "/deleteFriend", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
//    @ResponseBody
//    public String deleteFriend(
//            @ApiParam(value = "登录时获取的sessionId", required = true) @RequestParam String sessionId,
//            @ApiParam(value = "好友id", required = true) @RequestParam String fid) {
//        if (StringUtils.isNullorWhiteSpace(sessionId,fid)) {
//            return CommRetTemplate.TEMPLATE_PARAM_ERR;
//        }
//        return friendService.deleteFriend(sessionId, fid);
//    }

    @ApiOperation(value = "获取朋友圈列表", notes = "获取朋友圈列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/momentList", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.GET)
    @ResponseBody
    public String momentList(
            @ApiParam(value = "登录时获取的sessionId", required = true) @RequestParam String sessionId) {
        if (StringUtils.isNullorWhiteSpace(sessionId)) {
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        return friendService.getMomentList(sessionId);
    }

    @ApiOperation(value = "发表朋友圈", notes = "发表朋友圈，请将图片一并带上来，最多支持九张图片", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/addMoment", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    @ResponseBody
    public String addMoment(
            @ApiParam(value = "登录时获取的sessionId", required = true) @RequestParam String sessionId,
            @ApiParam(value = "朋友圈文字內容", required = true) @RequestParam String textContext,
            HttpServletRequest request,HttpServletResponse response) {
        if (StringUtils.isNullorWhiteSpace(sessionId)) {
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        return friendService.addMoment(request, response, sessionId, textContext);
    }

    @ApiOperation(value = "删除某条朋友圈动态", notes = "删除某条朋友圈动态", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/deleteMoment", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    @ResponseBody
    public String deleteMoment(
            @ApiParam(value = "登录时获取的sessionId", required = true) @RequestParam String sessionId,
            @ApiParam(value = "想要删除的动态Id", required = true) @RequestParam String momentId){

        if (StringUtils.isNullorWhiteSpace(sessionId)) {
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        return friendService.deleteMoment(sessionId, momentId);
    }

    @ApiOperation(value = "获取朋友圈图片", notes = "获取朋友圈图片", httpMethod = "GET", produces = MediaType.IMAGE_GIF_VALUE)
    @RequestMapping(value = "/moment/image", produces = {MediaType.IMAGE_GIF_VALUE}, method = RequestMethod.GET)
    public void image(HttpServletRequest request, HttpServletResponse response,
                                 @ApiParam(value = "用户id", required = true) @RequestParam String momentId,
                                 @ApiParam(value = "文件名", required = true) @RequestParam String fileName) {
        if (StringUtils.isNullorWhiteSpace(momentId, fileName)) {
            return;
        }
        String path =request.getSession().getServletContext().getRealPath("/")+ "moment\\/" +momentId + "\\/" + fileName;
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

}
