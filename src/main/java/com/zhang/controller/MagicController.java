package com.zhang.controller;

import com.zhang.model.Rresource;
import com.zhang.service.RresourceService;
import com.zhang.util.JsonUtil;
import com.zhang.util.Response;
import com.zhang.util.link.LinkFilter;
import com.zhang.util.link.Links;
import com.zhang.util.page.Page;
import com.zhang.util.page.PageParserTool;
import com.zhang.util.page.RequestAndResponseTool;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：zhangwn
 * @date ：Created in 2019/3/7 13:55
 * @modified By：
 */
@RestController
@RequestMapping("/magic")
public class MagicController {

    @Resource
    private RresourceService resourceService;

    /**
     * 获取列表
     */
    private final Logger logger = LoggerFactory.getLogger(MagicController.class);
    @RequestMapping("/queryMagnetic")
    public String queryMagnetic (HttpServletRequest request){
        String KeyWord = request.getParameter("KeyWord");
        logger.info(KeyWord);
        List<Rresource> resourcesList = getList(new String[]{"https://www.findcl.co/list?q=" + URLEncoder.encode(KeyWord)});
        return JsonUtil.getJson(new Response(resourcesList));
    }

    /**
     * 获取资源详情
     * @param request
     * @return
     */
    @RequestMapping("/queryMagneticDetails")
    public String queryMagneticDetails (HttpServletRequest request){
        String listUrl = request.getParameter("listUrl");
        logger.info(listUrl);
        Rresource resource = getDetails(listUrl);
        return JsonUtil.getJson(new Response(resource));
    }

    /**
     * 使用种子初始化 URL 队列
     *
     * @param seeds 种子 URL
     * @return
     */
    private void initCrawlerWithSeeds(String[] seeds) {
        for (int i = 0; i < seeds.length; i++){
            Links.addUnvisitedUrlQueue(seeds[i]);
        }
    }


    /**
     * 抓取过程
     *
     * @param seeds
     * @return
     */
    public List<Rresource> getList(String[] seeds) {
        List<Rresource> resourcesList = new ArrayList<Rresource>();

        //初始化 URL 队列
        initCrawlerWithSeeds(seeds);

        //定义过滤器，提取以 http://www.baidu.com 开头的链接
        LinkFilter filter = new LinkFilter() {
            public boolean accept(String url) {
                if (url.startsWith("https://www.findcl.co"))
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
                resourceService.insert(resource);
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

    public Rresource getDetails(String listUrl) {
        Rresource resource = resourceService.getResourceByUrl(listUrl);
        if(null != resource && StringUtils.isEmpty(resource.getDownloadUrl())){
            //初始化 URL 队列
            initCrawlerWithSeeds(new String[]{"https://www.findcl.co"+listUrl});

            //定义过滤器，提取以 http://www.baidu.com 开头的链接
            LinkFilter filter = new LinkFilter() {
                public boolean accept(String url) {
                    if (url.startsWith("https://www.findcl.co"))
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
                    resourceService.update(resource);

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
        return resource;
    }
}
