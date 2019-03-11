package com.zhang.service;

import com.zhang.model.Rkeyword;

import java.util.List;

public interface RkeywordService {
    public void statisticsKeyword(String keyword);

    /**
     * 查询热门搜索的关键词
     * @return
     */
    public List<Rkeyword> getHotKeyword();
}
