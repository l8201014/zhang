/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.zhang.model;


import java.io.Serializable;
import java.util.Date;


/**
 * Rresource 
 */
public class Rresource implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//columns START
	/**
	 * 
	 */
	private Integer id;
	/**
	 * 标题吧
	 */
	private String title;
	/**
	 * 爬取详细地址
	 */
	private String url;
	/**
	 * 文件类型
	 */
	private String fileType;
	/**
	 * 文件大小
	 */
	private String fileSize;
	/**
	 * 下载地址吧
	 */
	private String downloadUrl;
	/**
	 * 更新时间
	 */
	private String updateTime;
	/**
	 * 创建时间
	 */
	private String createTime;
	//columns END

	public Rresource(){
	}

	public Rresource(
		Integer id
	){
		this.id = id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return this.url;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public String getFileType() {
		return this.fileType;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	
	public String getFileSize() {
		return this.fileSize;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	
	public String getDownloadUrl() {
		return this.downloadUrl;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getCreateTime() {
		return this.createTime;
	}

	public String toString() {
		return "[" +
			"Id[]=" + getId() +"," +
			"Title[标题吧]=" + getTitle() +"," +
			"Url[爬取详细地址]=" + getUrl() +"," +
			"FileType[文件类型]=" + getFileType() +"," +
			"FileSize[文件大小]=" + getFileSize() +"," +
			"DownloadUrl[下载地址吧]=" + getDownloadUrl() +"," +
			"UpdateTime[更新时间]=" + getUpdateTime() +"," +
			"CreateTime[创建时间]=" + getCreateTime() +"," +
			"]";
	}
	
}

