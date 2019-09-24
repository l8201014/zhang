package com.zhang.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author ：zhangwn
 * @date ：Created in 2019/3/7 13:56
 * @modified By：
 */
public class JsonUtil {

    public static String objectToJsonStr(Object obj){
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(obj);
    }
}
