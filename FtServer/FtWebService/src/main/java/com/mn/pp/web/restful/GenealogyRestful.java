package com.mn.pp.web.restful;

import com.mn.pp.common.app.CommRetTemplate;
import com.mn.pp.core.utils.StringUtils;
import com.mn.pp.service.IFamilyMemberService;
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
 * Created by Administrator on 2017/2/7 0007.
 * 家庭成员app调用接口
 */
@Api(description = "家庭树相关接口")
@Controller
@RequestMapping("/genealogy")
public final class GenealogyRestful {

    @Autowired
    IFamilyMemberService familyMemberService;

    /**
     * 创建家庭成员接口
     *
     * @param familyMemberJson 家庭成员字典json
     * @return 操作結果 json 串
     */
    @ApiOperation(value = "创建家庭成员", notes = "创建家庭成员", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/createFamilyMember", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    @ResponseBody
    public String createFamilyMember(
            @ApiParam(value = "登录时得到的sessionId", required = true) @RequestParam String sessionId,
            @ApiParam(value = "家庭成员json字符串,所有字段都不能為空", required = true, example = "{\n" +
                    "    \"name\": null,\n" +
                    "    \"headurl\": null,\n" +
                    "    \"gendar\": null,\n" +
                    "    \"maritalstatus\": null,\n" +
                    "    \"politicalstatus\": null,\n" +
                    "    \"birthday\": null,\n" +
                    "    \"profession\": null,\n" +
                    "    \"father\": null,\n" +
                    "    \"mother\": null,\n" +
                    "    \"phonenumber\": null,\n" +
                    "    \"spouse\": null,\n" +
                    "    \"homeaddress\": null,\n" +
                    "    \"deeds\": null,\n" +
                    "    \"level\": 0\n" +
                    "}") @RequestParam String familyMemberJson) {
        if (StringUtils.isNullorWhiteSpace(familyMemberJson, sessionId)) {
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        return familyMemberService.createFamilyMember(sessionId, familyMemberJson);
    }

    /**
     * 更改家庭成員字段
     *
     * @param fmId       家庭成员唯一id
     * @param fieldName  要修改的字段名
     * @param fieldValue 字段值
     * @return 操作結果 json 串
     */
    @ApiOperation(value = "更改家庭成員字段", notes = "更改家庭成員字段", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/updateFamilyMember", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    @ResponseBody
    public String updateFamilyMember(
            @ApiParam(value = "登录时得到的sessionId", required = true) @RequestParam String sessionId,
            @ApiParam(value = "家庭成员id", required = true) @RequestParam String fmId,
            @ApiParam(value = "字段名", required = true, example = "age") @RequestParam String fieldName,
            @ApiParam(value = "字段名", required = true, example = "20这个就是上面age设的值") @RequestParam Object fieldValue) {
        if (StringUtils.isNullorWhiteSpace(sessionId, fmId, fieldName)) {
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        return familyMemberService.updateFamilyMember(sessionId, fmId, fieldName, fieldValue);
    }

    @ApiOperation(value = "删除家庭成员", notes = "删除家庭成员", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/deleteFamilyMember", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    @ResponseBody
    public String deleteFamilyMember(
            @ApiParam(value = "登录时得到的sessionId", required = true) @RequestParam String sessionId,
            @ApiParam(value = "家庭成员id", required = true) @RequestParam String fmId) {
        if (StringUtils.isNullorWhiteSpace(sessionId, fmId)) {
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        return familyMemberService.deleteFamilyMember(sessionId, fmId);
    }

    @ApiOperation(value = "获取用户所有家庭成员", notes = "获取用户所有家庭成员", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/AllFamilyMember", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.GET)
    @ResponseBody
    public String AllFamilyMember(
            @ApiParam(value = "登录时得到的sessionId", required = true) @RequestParam String sessionId) {

        if (StringUtils.isNullorWhiteSpace(sessionId)) {
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        return familyMemberService.findFamilyMember(sessionId);
    }

    @ApiOperation(value = "上传家庭成员图像头像", notes = "上传家庭成员图像，请将图像文件一起带上来，支持所有的图片格式", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/uploadPortrait", produces = MediaType.IMAGE_GIF_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public String uploadPortrait(
            @ApiParam(value = "登录时得到的sessionId", required = true) @RequestParam String sessionId,
            @ApiParam(value = "家庭成员id", required = true) @RequestParam String fmId,
            HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isNullorWhiteSpace(sessionId, fmId)) {
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        return familyMemberService.uploadHead(sessionId, fmId, request, response);
    }

    /**
     * 获取用户或者关心的人头像
     *
     * @param response
     * @param fileName
     */

    @RequestMapping(value = "/headPortrait", produces = {MediaType.IMAGE_GIF_VALUE}, method = RequestMethod.GET)
    public void headPortrait(HttpServletRequest request, HttpServletResponse response,
                             @ApiParam(value = "家庭成员id", required = true) @RequestParam String fmId,
                             @ApiParam(value = "图片文件名", required = true) @RequestParam String fileName) {
        if (StringUtils.isNullorWhiteSpace(fmId, fileName)) {
            return;
        }
        String path = request.getSession().getServletContext().getRealPath("/") + "/familyMember/" + fmId + "/" + fileName;
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
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    //e.printStackTrace();
                }
            }
        }
    }

    @ApiOperation(value = "创建家庭树", notes = "创建家庭树", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/createGenealogy", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public String createGenealogy(
            @ApiParam(value = "登录时得到的sessionId", required = true) @RequestParam String sessionId,
            @ApiParam(value = "家庭树显示名称", required = true) @RequestParam String showName,
            HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isNullorWhiteSpace(sessionId, showName)) {
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        return familyMemberService.createGenealogy(sessionId, showName);
    }


    @ApiOperation(value = "获取家庭树下面所有的家庭成员", notes = "获取家庭树下面所有的家庭成员", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/gFamilyMembers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public String gFamilyMembers(
            @ApiParam(value = "登录时得到的sessionId", required = true) @RequestParam String sessionId,
            @ApiParam(value = "家庭树Id", required = true) @RequestParam String gId) {

        if (StringUtils.isNullorWhiteSpace(sessionId, gId)) {
            return CommRetTemplate.TEMPLATE_PARAM_ERR;
        }
        return familyMemberService.findFamilyMemberByGId(sessionId, gId);
    }

}
