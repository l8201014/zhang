/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 */

package com.zhang.model;


import java.io.Serializable;


/**
 * Rkeyword 
 */
public class Rkeyword implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//columns START
	/**
	 * 
	 */
	private Integer id;
	/**
	 * 搜索关键字
	 */
	private String keyword;
	/**
	 * 搜索量
	 */
	private Integer searchNum;
	/**
	 * 
	 */
	private java.sql.Timestamp updateTime;
	/**
	 * 
	 */
	private java.sql.Timestamp createTime;
	//columns END

	public Rkeyword(){
	}

	public Rkeyword(
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

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getKeyword() {
		return this.keyword;
	}

	public void setSearchNum(Integer searchNum) {
		this.searchNum = searchNum;
	}
	
	public Integer getSearchNum() {
		return this.searchNum;
	}

	public void setUpdateTime(java.sql.Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	public java.sql.Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}
	
	public java.sql.Timestamp getCreateTime() {
		return this.createTime;
	}

	public String toString() {
		return "[" +
			"Id[]=" + getId() +"," +
			"Keyword[搜索关键字]=" + getKeyword() +"," +
			"SearchNum[搜索量]=" + getSearchNum() +"," +
			"UpdateTime[]=" + getUpdateTime() +"," +
			"CreateTime[]=" + getCreateTime() +"," +
			"]";
	}
	
}

