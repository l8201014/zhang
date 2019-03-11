package com.zhang.controller;

import com.zhang.entity.HotKeywordRsp;
import com.zhang.model.Rkeyword;
import com.zhang.service.RkeywordService;
import com.zhang.util.JsonUtil;
import com.zhang.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：zhangwn
 * @date ：Created in 2019/3/11 15:03
 * @modified By：
 */
@CrossOrigin
@RestController
@RequestMapping("/hotSearch")
public class HotSearchController {
    private final Logger logger = LoggerFactory.getLogger(HotSearchController.class);
    @Resource
    private RkeywordService rkeywordService;

    /**
     * 热门关键词
     * @param request
     * @return
     */
    @RequestMapping("/queryHotKeyword")
    public String queryHotKeyword(HttpServletRequest request){
        HotKeywordRsp rsp = new HotKeywordRsp();
        List<Rkeyword> rkeywords = rkeywordService.getHotKeyword();
        List<String> keyworList = new ArrayList<>();
        for(Rkeyword rkeyword : rkeywords){
            keyworList.add(rkeyword.getKeyword());
        }
        rsp.setKeywords(keyworList);
        return JsonUtil.objectToJsonStr(new Response(rsp));
    }
}
