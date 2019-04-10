package com.zhang.service;

import com.zhang.model.Rkeyword;

import java.util.List;
import java.util.Map;

public interface RkeywordService {
    public void statisticsKeyword(String keyword);

    /**
     * 查询热门搜索的关键词
     * @return
     */
    public List<Rkeyword> getHotKeyword();

    /**
     * 根据日期查询数据
     * @param map
     * @return
     */
    List<Rkeyword> getKeyWordByDate(Map<String, Object> map);
}
