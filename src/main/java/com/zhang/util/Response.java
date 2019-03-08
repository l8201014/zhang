package com.zhang.util;

import java.io.Serializable;

/**
 * @author ：zhangwn
 * @date ：Created in 2019/3/7 14:22
 * @modified By：
 */
public class Response implements Serializable {
    private static final long serialVersionUID = 7L;
    private String respCode;
    private String respMsg;
    private Object Date;
    private PageConfig pageConfig;

    public Response(Object Date,PageConfig pageConfig){
        this.respCode = "200";
        this.Date = Date;
        this.pageConfig = pageConfig;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public Object getDate() {
        return Date;
    }

    public void setDate(Object date) {
        Date = date;
    }

    public PageConfig getPageConfig() {
        return pageConfig;
    }

    public void setPageConfig(PageConfig pageConfig) {
        this.pageConfig = pageConfig;
    }
}
