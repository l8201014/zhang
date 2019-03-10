package com.zhang.entity;

import com.zhang.util.PageConfig;

import java.io.Serializable;

public class BesaRsp implements Serializable {
    private PageConfig PageConfig;

    public com.zhang.util.PageConfig getPageConfig() {
        return PageConfig;
    }

    public void setPageConfig(com.zhang.util.PageConfig pageConfig) {
        PageConfig = pageConfig;
    }
}
