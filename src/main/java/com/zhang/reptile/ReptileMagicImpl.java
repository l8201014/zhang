package com.zhang.reptile;

import com.zhang.controller.MagicController;
import com.zhang.model.Rresource;
import com.zhang.reptile.api.ReptileMagic;
import com.zhang.service.RkeywordService;
import com.zhang.service.RresourceService;
import com.zhang.util.JsonUtil;
import com.zhang.util.UrlUtrl;
import com.zhang.util.link.LinkFilter;
import com.zhang.util.link.Links;
import com.zhang.util.page.Page;
import com.zhang.util.page.PageParserTool;
import com.zhang.util.page.RequestAndResponseTool;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：zhangwn
 * @date ：Created in 2019/4/10 10:03
 * @modified By：
 */
@Service
public class ReptileMagicImpl implements ReptileMagic {
    private final Logger logger = LoggerFactory.getLogger(ReptileMagicImpl.class);
    @Resource
    private RresourceService resourceService;
    /**
     * 使用种子初始化 URL 队列
     *
     * @param seeds 种子 URL
     * @return
     */
    private static void initCrawlerWithSeeds(String[] seeds) {
        for (int i = 0; i < seeds.length; i++){
            Links.addUnvisitedUrlQueue(seeds[i]);
        }
    }


    /**
     * 抓取过程
     *
     * @param keyWord
     * @return
     */
    public List<Rresource> getList(String keyWord, String pageIndex) {
        Map<String,Object> map = new HashMap<>();
        map.put("keyWord",keyWord);
        map.put("pagesize",(Integer.parseInt(pageIndex) - 1) * 10);
        List<Rresource> resourcesList = resourceService.getListByKeyWord(map);
        if(null != resourcesList && resourcesList.size() > 0){
            return resourcesList;
        }
        String[] seeds = new String[]{UrlUtrl.SB_URL + "/list?q=" + URLEncoder.encode(keyWord) + "&page=" + pageIndex};
        //初始化 URL 队列
        initCrawlerWithSeeds(seeds);

        //循环条件：待抓取的链接不空且抓取的网页不多于 1000
        while (!Links.unVisitedUrlQueueIsEmpty()  && Links.getVisitedUrlNum() <= 1000) {

            //先从待访问的序列中取出第一个；
            String visitUrl = (String) Links.removeHeadOfUnVisitedUrlQueue();
            if (visitUrl == null){
                continue;
            }

            //根据URL得到page;
            Page page = RequestAndResponseTool.sendRequstAndGetResponse(visitUrl);

            //对page进行处理： 访问DOM的某个标签
            Elements es = PageParserTool.select(page,"a");
            logger.info(es.size()+"");
            for (Element element : es){
                if(!element.toString().contains("<strong>")){
                    continue;
                }
                Rresource resource = new Rresource();
                //System.out.println(element);

                String str = element.toString();
                Pattern titlePattern = Pattern.compile("(?<=\\\").*?(?=\\\")");
                Matcher titleMatcher = titlePattern.matcher(str);
                List<String> titleList = new ArrayList<String>();
                while(titleMatcher.find()) {
                    titleList.add(titleMatcher.group(0));
                }
                List<String> stringList = new ArrayList<String>();
                //创建Pattern并进行匹配
                Pattern pattern= Pattern.compile("(?<=<strong>).*?(?=</strong>)");
                Matcher matcher=pattern.matcher(str);
                //将所有匹配的结果打印输出
                while(matcher.find()) {
                    stringList.add(matcher.group());
                }
                logger.info("列表地址："+titleList.get(0)+",标题："+titleList.get(2)+
                        ",类型："+stringList.get(0)+",大小："+stringList.get(1)+",收录时间："+stringList.get(2));
                resource.setTitle(titleList.get(2));
                resource.setUrl(titleList.get(0));
                resource.setFileType(stringList.get(0));
                resource.setCreateTime(stringList.get(2));
                resource.setFileSize(stringList.get(1));
                resource.setClickNum(0);
                Rresource isresource = resourceService.getResourceByUrl(titleList.get(0));
                if(null == isresource){
                    resourceService.insert(resource);
                }else {
                    resource.setId(isresource.getId());
                    resource.setClickNum(isresource.getClickNum());
                }
                resourcesList.add(resource);
            }
//            //将保存文件
//            FileTool.saveToLocal(page);
//
//            //将已经访问过的链接放入已访问的链接中；
//            Links.addVisitedUrlSet(visitUrl);
//
//            //得到超链接
//            Set<String> links = PageParserTool.getLinks(page,"img");
//            for (String link : links) {
//                Links.addUnvisitedUrlQueue(link);
//                System.out.println("新增爬取路径: " + link);
//            }
        }
        return resourcesList;
    }

    public Rresource getDetails(String id) {
        Rresource resource = resourceService.getResourceById(Integer.parseInt(id));
        if(null != resource && StringUtils.isEmpty(resource.getDownloadUrl())){
            //初始化 URL 队列
            initCrawlerWithSeeds(new String[]{UrlUtrl.SB_URL+resource.getUrl()});

            //定义过滤器，提取以 http://www.baidu.com 开头的链接
            LinkFilter filter = new LinkFilter() {
                public boolean accept(String url) {
                    if (url.startsWith(UrlUtrl.SB_URL))
                        return true;
                    else
                        return false;
                }
            };

            //循环条件：待抓取的链接不空且抓取的网页不多于 1000
            while (!Links.unVisitedUrlQueueIsEmpty()  && Links.getVisitedUrlNum() <= 1000) {

                //先从待访问的序列中取出第一个；
                String visitUrl = (String) Links.removeHeadOfUnVisitedUrlQueue();
                if (visitUrl == null){
                    continue;
                }

                //根据URL得到page;
                Page page = RequestAndResponseTool.sendRequstAndGetResponse(visitUrl);

                //对page进行处理： 访问DOM的某个标签
                Elements es = PageParserTool.select(page,"code");
//            if(!es.isEmpty()){
//                System.out.println("下面将打印所有a标签： ");
//                System.out.println(es);
//            }
                for (Element element : es){
                    // System.out.println(element);
                    Pattern pattern= Pattern.compile("(?<=325fe1c2>).*?(?=</code>)");
                    Matcher matcher=pattern.matcher(element.toString());
                    List<String> stringList = new ArrayList<String>();
                    while(matcher.find()) {
                        logger.info(matcher.group());
                        stringList.add(matcher.group());
                    }
                    resource.setDownloadUrl(stringList.get(0));
                }

//            //将保存文件
//            FileTool.saveToLocal(page);
//
//            //将已经访问过的链接放入已访问的链接中；
//            Links.addVisitedUrlSet(visitUrl);
//
//            //得到超链接
//            Set<String> links = PageParserTool.getLinks(page,"img");
//            for (String link : links) {
//                Links.addUnvisitedUrlQueue(link);
//                System.out.println("新增爬取路径: " + link);
//            }
            }
        }
        resource.setClickNum(resource.getClickNum()+1);
        resourceService.update(resource);
        return resource;
    }

    public List<Rresource> getListNew(String keyWord) {
        List<Rresource> resourceList = new ArrayList<>();
        try {
            String newUrl = UrlUtrl.SB_URL + "/list?q=" + URLEncoder.encode(keyWord);//transformUrl(url, keyword, page);
            String html = Jsoup.connect(newUrl).get().body().html();
            Pattern urlPattern = Pattern.compile("(?<=href=\").*?(?=\\\")");
            Matcher urlMatcher = urlPattern.matcher(html);
            List<String> urlList = new ArrayList<String>();
            logger.info("*********************************************************************");
            while (urlMatcher.find()) {
                if (urlMatcher.group().contains("/list/")) {
                    logger.info(urlMatcher.group());
                    urlList.add(urlMatcher.group(0));
                }
            }
            Pattern titlePattern = Pattern.compile("(?<=title=\").*?(?=\\\")");
            Matcher titleMatcher = titlePattern.matcher(html);
            List<String> titleList = new ArrayList<String>();
            logger.info("*********************************************************************");
            while (titleMatcher.find()) {
                if (titleMatcher.group().contains(keyWord)) {
                    logger.info(titleMatcher.group());
                    titleList.add(titleMatcher.group(0));
                }
            }
            logger.info("*********************************************************************");
            List<String> stringList = new ArrayList<String>();
            //创建Pattern并进行匹配
            Pattern pattern = Pattern.compile("(?<=<strong>).*?(?=</strong>)");
            Matcher matcher = pattern.matcher(html);
            //将所有匹配的结果打印输出
            while (matcher.find()) {
                logger.info(matcher.group());
                stringList.add(matcher.group());
            }
            for (int i = 0; i < urlList.size(); i++) {
                Rresource resource = new Rresource();
                resource.setTitle(titleList.get(i));
                resource.setUrl(urlList.get(i));
                resource.setFileType(stringList.get(i*3));
                resource.setCreateTime(stringList.get((i*3)+2));
                resource.setFileSize(stringList.get((i*3)+1));
                logger.info(JsonUtil.getJson(resource));
                resourceService.insert(resource);
                resourceList.add(resource);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resourceList;
    }

    public Rresource getDetailsNew(String listUrl) {
        Rresource resource = resourceService.getResourceByUrl(listUrl);
        try {
            if (null != resource && StringUtils.isEmpty(resource.getDownloadUrl())) {
                String newUrl = UrlUtrl.SB_URL + listUrl;//transformUrl(url, keyword, page);
                String html = Jsoup.connect(newUrl).get().body().html();
                Pattern pattern= Pattern.compile("(?<=325fe1c2>).*?(?=</code>)");
                Matcher matcher=pattern.matcher(html);
                List<String> stringList = new ArrayList<String>();
                while(matcher.find()) {
                    logger.info(matcher.group());
                    stringList.add(matcher.group());
                }
                resource.setDownloadUrl(stringList.get(0));
//                resourceService.update(resource);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resource;
    }
    public Integer getTotalNum(String keyWord){
        Integer totalNum = 0;
        try {
            String newUrl = UrlUtrl.SB_URL + "/list?q=" + URLEncoder.encode(keyWord);//transformUrl(url, keyword, page);
            String html = Jsoup.connect(newUrl).get().body().html();
            Pattern pattern= Pattern.compile("(?<=傻逼吧为您找到相关结果约).*?(?=个)");
            Matcher matcher=pattern.matcher(html);
            //将所有匹配的结果打印输出
            while(matcher.find()) {
                logger.info(matcher.group());
                totalNum = Integer.parseInt(matcher.group().trim());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return totalNum;
    }

    public Rresource getDetailsTask(Rresource resource) {
        if(null != resource && StringUtils.isEmpty(resource.getDownloadUrl())){
            //初始化 URL 队列
            initCrawlerWithSeeds(new String[]{UrlUtrl.SB_URL+resource.getUrl()});

            //循环条件：待抓取的链接不空且抓取的网页不多于 1000
            while (!Links.unVisitedUrlQueueIsEmpty()  && Links.getVisitedUrlNum() <= 1000) {

                //先从待访问的序列中取出第一个；
                String visitUrl = (String) Links.removeHeadOfUnVisitedUrlQueue();
                if (visitUrl == null){
                    continue;
                }

                //根据URL得到page;
                Page page = RequestAndResponseTool.sendRequstAndGetResponse(visitUrl);

                //对page进行处理： 访问DOM的某个标签
                Elements es = PageParserTool.select(page,"code");
//            if(!es.isEmpty()){
//                System.out.println("下面将打印所有a标签： ");
//                System.out.println(es);
//            }
                for (Element element : es){
                    // System.out.println(element);
                    Pattern pattern= Pattern.compile("(?<=325fe1c2>).*?(?=</code>)");
                    Matcher matcher=pattern.matcher(element.toString());
                    List<String> stringList = new ArrayList<String>();
                    while(matcher.find()) {
                        logger.info(matcher.group());
                        stringList.add(matcher.group());
                    }
                    resource.setDownloadUrl(stringList.get(0));
                }
            }
        }
        resource.setClickNum(resource.getClickNum()+1);
        resourceService.update(resource);
        return resource;
    }

    public void getListTask(String[] seeds) {
        //String[] seeds = new String[]{UrlUtrl.SB_URL + "/list?q=" + URLEncoder.encode(keyWord) + "&page=" + pageIndex};
        //初始化 URL 队列
        initCrawlerWithSeeds(seeds);

        //定义过滤器，提取以 http://www.baidu.com 开头的链接
        LinkFilter filter = new LinkFilter() {
            public boolean accept(String url) {
                if (url.startsWith(UrlUtrl.SB_URL))
                    return true;
                else
                    return false;
            }
        };

        //循环条件：待抓取的链接不空且抓取的网页不多于 1000
        while (!Links.unVisitedUrlQueueIsEmpty()) {

            //先从待访问的序列中取出第一个；
            String visitUrl = (String) Links.removeHeadOfUnVisitedUrlQueue();
            if (visitUrl == null){
                continue;
            }

            //根据URL得到page;
            Page page = RequestAndResponseTool.sendRequstAndGetResponse(visitUrl);

            //对page进行处理： 访问DOM的某个标签
            Elements es = PageParserTool.select(page,"a");
            logger.info(es.size()+"");
            for (Element element : es){
                if(!element.toString().contains("<strong>")){
                    continue;
                }
                Rresource resource = new Rresource();
                //System.out.println(element);

                String str = element.toString();
                Pattern titlePattern = Pattern.compile("(?<=\\\").*?(?=\\\")");
                Matcher titleMatcher = titlePattern.matcher(str);
                List<String> titleList = new ArrayList<String>();
                while(titleMatcher.find()) {
                    titleList.add(titleMatcher.group(0));
                }
                List<String> stringList = new ArrayList<String>();
                //创建Pattern并进行匹配
                Pattern pattern= Pattern.compile("(?<=<strong>).*?(?=</strong>)");
                Matcher matcher=pattern.matcher(str);
                //将所有匹配的结果打印输出
                while(matcher.find()) {
                    stringList.add(matcher.group());
                }
                logger.info("列表地址："+titleList.get(0)+",标题："+titleList.get(2)+
                        ",类型："+stringList.get(0)+",大小："+stringList.get(1)+",收录时间："+stringList.get(2));
                resource.setTitle(titleList.get(2));
                resource.setUrl(titleList.get(0));
                resource.setFileType(stringList.get(0));
                resource.setCreateTime(stringList.get(2));
                resource.setFileSize(stringList.get(1));
                resource.setClickNum(0);
                Rresource isresource = resourceService.getResourceByUrl(titleList.get(0));
                if(null == isresource){
                    resourceService.insert(resource);
                }else {
                    resource.setId(isresource.getId());
                    resource.setClickNum(isresource.getClickNum());
                }
            }
//            //将保存文件
//            FileTool.saveToLocal(page);
//
//            //将已经访问过的链接放入已访问的链接中；
//            Links.addVisitedUrlSet(visitUrl);
//
//            //得到超链接
//            Set<String> links = PageParserTool.getLinks(page,"img");
//            for (String link : links) {
//                Links.addUnvisitedUrlQueue(link);
//                System.out.println("新增爬取路径: " + link);
//            }
        }
    }
}
