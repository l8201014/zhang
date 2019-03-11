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
    private Object data;

    public Response(Object data){
        this.respCode = "200";
        this.respMsg = "成功";
        this.data = data;
    }
    public Response(String respCode,String respMsg){
        this.respCode = respCode;
        this.respMsg = respMsg;
        this.data = null;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
