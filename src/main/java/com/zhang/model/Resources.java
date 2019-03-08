package com.zhang.model;

import java.io.Serializable;

/**
 * @author ：zhangwn
 * @date ：Created in 2019/3/7 14:02
 * @modified By：
 */
public class Resources implements Serializable {
    /**
     * @Fields:serialVersionUID :
     */
    private static final long serialVersionUID = 1L;
    private String title;
    private String listUrl;
    private String fileType;
    private String fileSize;
    private String creatorTime;
    private String magicUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getListUrl() {
        return listUrl;
    }

    public void setListUrl(String listUrl) {
        this.listUrl = listUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getCreatorTime() {
        return creatorTime;
    }

    public void setCreatorTime(String creatorTime) {
        this.creatorTime = creatorTime;
    }

    public String getMagicUrl() {
        return magicUrl;
    }

    public void setMagicUrl(String magicUrl) {
        this.magicUrl = magicUrl;
    }
}
