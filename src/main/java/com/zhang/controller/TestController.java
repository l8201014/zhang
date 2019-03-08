package com.zhang.controller;

import com.zhang.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.mapper.JsonMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：zhangwn
 * @date ：Created in 2019/1/25 15:14
 * @modified By：
 */
@RestController
@RequestMapping("/test")
public class TestController {
    private static final JsonMapper jsonMapper = JsonMapper.nonEmptyMapper();
    @RequestMapping("/hello")
    public String hello(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","zhang");
        map.put("phone","13567121794");
        User user = new User();
//        user.setUsername("zhang");
//        user.setPassword("aa123456");
//        user.setPhone("13567121794");
        return jsonMapper.toJson(user);
    }
    public static void main(String[] args){
//        int[] a = {1,7,3,5,2,9,4,6,8};
//        for(int i = 0;i <= a.length-1;i++){
//            for(int j = 0;j < a.length-1-i;j++){
//                if(a[j] > a[j+1]){
//                    int trem = a[j];
//                    a[j] = a[j+1];
//                    a[j+1] = trem;
//                }
//            }
//        }
//        System.out.println(a.length);
//        int b = 0;
//        while (b != a.length){
//            System.out.println(a[b]);
//            b++;
//        }


        String[] strings = {"f","a","e","c","u","w","m","q","s","l","p"};
        for(int i1 = 0;i1 <= strings.length-1;i1++){
            for(int j1 = 0; j1 < strings.length-1-i1;j1++){
                if(strings[j1].charAt(0) > strings[j1+1].charAt(0)){
                    String tem = strings[j1];
                    strings[j1] = strings[j1+1];
                    strings[j1+1] = tem;
                }
            }
        }
        for(String str : strings){
            System.out.println(str);
        }
    }
}
