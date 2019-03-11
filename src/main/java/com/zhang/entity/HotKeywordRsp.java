package com.zhang.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：zhangwn
 * @date ：Created in 2019/3/11 15:10
 * @modified By：
 */
public class HotKeywordRsp implements Serializable {
    private List<String> keywords;

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
