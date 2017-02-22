package com.mn.pp.core.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class JackSonUtil {
    private static ObjectMapper _jackson = null;
    private static Logger logger = LoggerFactory.getLogger(JackSonUtil.class.getName());

    private static synchronized ObjectMapper getObjMapperInst() {
        if (_jackson == null) {
            _jackson = getDefaultObjectMapper();
        }
        return _jackson;
    }

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }

    public static String obj2Json(Object obj) {
        ObjectMapper mapper = getObjMapperInst();
        try {
            String jsonStr = mapper.writeValueAsString(obj);
            return jsonStr;
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    public static <T>T json2Obj(String str, Class<T> c) {
        ObjectMapper mapper = getObjMapperInst();
        T obj = null;
        try {
            obj = mapper.readValue(str, c);
        } catch (Exception e) {
            logger.error("", e);
        }
        return obj;
    }

    public static <T> List<T> json2List(String str, Class<T> c) {
        ObjectMapper mapper = getObjMapperInst();
        List<T> tList = null;
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, c);
        try {
            tList = mapper.readValue(str, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tList;
    }
}
