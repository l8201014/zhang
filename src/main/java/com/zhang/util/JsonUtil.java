package com.zhang.util;

import org.springside.modules.mapper.JsonMapper;

/**
 * @author ：zhangwn
 * @date ：Created in 2019/3/7 13:56
 * @modified By：
 */
public class JsonUtil {
    private static final JsonMapper jsonMapper = JsonMapper.nonEmptyMapper();

    public static String getJson(Object object){
        return jsonMapper.toJson(object);
    }
}
