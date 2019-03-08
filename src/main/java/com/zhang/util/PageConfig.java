package com.zhang.util;

/**
 * 分页工具
 * 
 * @author Fan.YuFeng
 * 
 */
public class PageConfig {
	/**
	 * 每页记录数
	 * 
	 * @param pageSize
	 */
	private Integer pageSize = 10;
	/**
	 * 页码
	 * 
	 * @param pageIndex
	 */
	private Integer pageIndex = 1;
	/**
	 * 总记录数
	 * 
	 * @param totalCount
	 */
	private Integer totalCount = null;

	
	private Integer totalPage = null;
	
	
	
	public Integer getTotalPage() {
		if(totalCount == null){
			return 0;
		}
		return (totalCount  +  pageSize  - 1) / pageSize;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageSize() {
		return this.pageSize;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageIndex() {
		return this.pageIndex;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalCount() {
		return this.totalCount;
	}
	
	public static void main(String[] args) {
		
		PageConfig p = new PageConfig();
		p.setPageIndex(1);
		p.setTotalCount(71);
		System.out.println(p.getTotalPage());
	}
}
