package com.zhang.task;

import com.zhang.model.Rkeyword;
import com.zhang.model.Rresource;
import com.zhang.reptile.api.ReptileMagic;
import com.zhang.service.RkeywordService;
import com.zhang.service.RresourceService;
import com.zhang.util.DateUtil;
import com.zhang.util.UrlUtrl;
import javafx.beans.binding.ObjectExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：zhangwn
 * @date ：Created in 2019/4/10 9:56
 * @modified By：
 */
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class MagicTask {
    private final Logger logger = LoggerFactory.getLogger(MagicTask.class);
    @Resource
    private ReptileMagic reptileMagic;
    @Resource
    private RresourceService resourceService;
    @Resource
    private RkeywordService rkeywordService;

//    @Scheduled(cron = "0 0 0 * * ?")
    @Scheduled(cron = "${task.time}")
    public void getMagneticUrlTasks(){
        logger.info("getMagneticUrlTasks定时任务开始....");
        List<Rresource> rresourceList = resourceService.getNoUrlList();
        if(null != rresourceList && rresourceList.size() > 0){
            for(Rresource resource : rresourceList){
                logger.info("资源id" + resource.getId());
                reptileMagic.getDetailsTask(resource);
            }
        }
        logger.info("getMagneticUrlTasks定时任务结束....");
    }

    @Scheduled(cron = "${task.time}")
    public void getListTasks(){
        logger.info("getListTasks定时任务开始....");
        //查询条件-----获取昨天日期
        Map<String, Object> map = new HashMap<>();
        map.put("yesterday", DateUtil.getYesterdayByDate());
        logger.info("昨天日期：" + DateUtil.getYesterdayByDate());
        //查询所有昨天搜索的关键字
        List<Rkeyword> keyWordList = rkeywordService.getKeyWordByDate(map);
        //int pageSize = reptileMagic.getTotalNum(keyWord)/10 + 1;//
        //String[] seeds = new String[pageSize * keyWordList.size()];
        if(null != keyWordList && keyWordList.size() > 0){
            for(Rkeyword keyword : keyWordList){
                int pageSize = reptileMagic.getTotalNum(keyword.getKeyword())/10 + 1;//关键字的总页数
                String[] seeds = new String[pageSize];
                for(int j = 1;j <= pageSize;j++){
                    seeds[j-1] = UrlUtrl.SB_URL + "/list?q=" + URLEncoder.encode(keyword.getKeyword()) + "&page=" + j;
                }
                reptileMagic.getListTask(seeds);
            }
        }
        logger.info("getListTasks定时任务结束....");
        //String[] seeds = new String[]{UrlUtrl.SB_URL + "/list?q=" + URLEncoder.encode(keyWord) + "&page=" + pageIndex};
    }
    public static void main(String[] args){
        System.out.println(261/10);
    }
}
