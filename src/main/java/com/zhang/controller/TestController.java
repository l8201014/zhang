package com.zhang.controller;

import com.zhang.model.User;
import com.zhang.util.JsonUtil;
import com.zhang.util.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author ：zhangwn
 * @date ：Created in 2019/1/25 15:14
 * @modified By：
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/hello")
    public String hello(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","zhang");
        map.put("phone","13567121794");
        User user = new User();
//        user.setUsername("zhang");
//        user.setPassword("aa123456");
//        user.setPhone("13567121794");
        return JsonUtil.objectToJsonStr(new Response(user));
    }
//    public static void main(String[] args){
////        int[] a = {1,7,3,5,2,9,4,6,8};
////        for(int i = 0;i <= a.length-1;i++){
////            for(int j = 0;j < a.length-1-i;j++){
////                if(a[j] > a[j+1]){
////                    int trem = a[j];
////                    a[j] = a[j+1];
////                    a[j+1] = trem;
////                }
////            }
////        }
////        System.out.println(a.length);
////        int b = 0;
////        while (b != a.length){
////            System.out.println(a[b]);
////            b++;
////        }
//
//
//        String[] strings = {"f","a","e","c","u","w","m","q","s","l","p"};
//        for(int i1 = 0;i1 <= strings.length-1;i1++){
//            for(int j1 = 0; j1 < strings.length-1-i1;j1++){
//                if(strings[j1].charAt(0) > strings[j1+1].charAt(0)){
//                    String tem = strings[j1];
//                    strings[j1] = strings[j1+1];
//                    strings[j1+1] = tem;
//                }
//            }
//        }
//        for(String str : strings){
//            System.out.println(str);
//        }
//    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Integer maxAmt = Integer.parseInt(sc.nextLine());
        String goodsAmt = sc.nextLine();
        String[] goodsAmts = goodsAmt.split(" ");
        //System.out.println();
        //先进行排序
        for(int i = 0;i <= goodsAmts.length-1;i++){
            for(int j = 0;j < goodsAmts.length-1-i;j++){
                if(Integer.parseInt(goodsAmts[j]) > Integer.parseInt(goodsAmts[j+1])){
                    String trem = goodsAmts[j];
                    goodsAmts[j] = goodsAmts[j+1];
                    goodsAmts[j+1] = trem;
                }
            }
        }
        int sumAmt = 0;
        int result = 0;
        for(String amt : goodsAmts){
            sumAmt = sumAmt + Integer.parseInt(amt);
            if(sumAmt > maxAmt){
                result = sumAmt - Integer.parseInt(amt);
                break;
            }else{
                result = sumAmt;
            }
        }
        System.out.println(result);
    }

//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        String[][] codess = {{"A","B","C","D","E","F","G","H","I"},{"J","K","L","M","N","O","P","Q","R"},{"S","T","U","V","W","X","Y","Z","*"}};
//        String date = sc.nextLine();
//        String string = sc.nextLine();
//        String[] dates = date.split(" ");
//        String[][] newCodess = new String[3][9];
//        //思路：往左移动看做，索引值减少多少，如果减到小于0，则需要加上移动几轮的数量算出以后都是数组，在循环匹配字符串
//
//        //得到新数组
//        for(int i = 0;i < codess.length;i++){
//            //int month = Integer.parseInt(dates[0]) - 1;
//            int newi = i + (codess.length - (Integer.parseInt(dates[0]) - 1) % codess.length);
//            //int newi = i - (Integer.parseInt(dates[0]) - 1);//计算移动后的位置
//            if(newi > codess.length - 1){
//                newi = newi - codess.length;
//            }
//            String[] codes = codess[i];
//            for(int j = 0;j < codes.length;j++){
//                int newj = j + (codes.length - (Integer.parseInt(dates[1]) - 1) % codes.length);
//                //int newj = j - (Integer.parseInt(dates[1]) - 1);//计算移动后的位置
//                if(newj > codes.length - 1){
//                    newj = newj - codes.length;
//                }
//                newCodess[newi][newj] = codess[i][j];
//            }
//        }
//        //开始匹配字母
//        StringBuffer result = new StringBuffer();
//        for(int a = 0;a < string.length();a++){
//            String letter = string.substring(a,a+1);
//            //因为存的是*，如果空格转换成"*"
//            if(letter.equals(" ")){
//                letter = "*";
//            }
//            for(int b = 0;b < newCodess.length;b++){
//                for(int c = 0;c < newCodess[b].length;c++){
//                    if(letter.equals(newCodess[b][c])){
//                        result.append(b+1);
//                        result.append(c+1);
//                        result.append(" ");
//                    }
//                }
//            }
//        }
//        System.out.println(result.toString());
//    }
}
