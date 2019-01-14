package com.buxiu.bootexample.interceptor; 

import java.util.ArrayList; 
import java.util.List;

/**
 * mybatis分页保存信息
 * @author bubuxiu@gmail.com
 * 
 */
@SuppressWarnings("serial")
public class PageInfo<T> extends ArrayList<T>{  
	
	private int pageNum;
    private int pageSize;
    private int startRow;
    private int endRow;
    private long total;
    private int pages;
    private List<T> list;
    
    
    public PageInfo(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize; 
    } 
    public List<T> getList() {
		return list;
	} 

	public void setList(List<T> list) {
		this.list = list;
	} 
	public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    } 
}
