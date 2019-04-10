package com.zhang.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：zhangwn
 * @date ：Created in 2019/4/10 14:12
 * @modified By：
 */
public class DateUtil {
    /**
     * 获取昨天日期
     * @return
     */
    public static String getYesterdayByDate(){
        //实例化当天的日期
        Date today = new Date();
        //用当天的日期减去昨天的日期
        Date yesterdayDate = new Date(today.getTime()-86400000L);
        String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(yesterdayDate);
        return yesterday;
    }

    public static void main(String[] args){
        System.out.println(getYesterdayByDate());
    }
}
