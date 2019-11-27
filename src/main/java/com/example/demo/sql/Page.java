package com.example.demo.sql;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.example.demo.model.BaseModel;


public class Page<T1 extends BaseModel> {

	/**
	 * 
	 */

	public final static Integer DEFAULT_PAGE_ON = 1;

	public final static Integer DEFAULT_PAGE_SIZE = 10;

	// 当前页码
	private Integer pageOn;

	// 每页显示数量
	private Integer pageSize;

	// 总页数
	private Integer totalPage;

	// 总记录数
	private Integer total;

	// 数据
	private List<?> rows;

	public Page(List<T1> rows,T1 t) {
		this.pageOn = t.getPage();
		this.pageSize = t.getLimit();
		this.total = t.getTotal();
		this.rows = rows;
	}

	public Integer getPageOn() {
		return pageOn;
	}

	public void setPageOn(Integer pageOn) {
		this.pageOn = pageOn;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
		this.totalPage = (total + this.pageSize - 1) / this.pageSize;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public Page() {
		this.pageOn=DEFAULT_PAGE_ON;
		this.pageSize=DEFAULT_PAGE_SIZE;
	}

	public Page(Integer pageOn) {
		this.pageOn = pageOn;
		this.pageSize=DEFAULT_PAGE_SIZE;
	}

	public Page(Integer pageOn, Integer pageSize) {
		this.pageOn = pageOn;
		this.pageSize = pageSize;
	}

	public RowBounds getPageInfo() {
		int offset = (this.pageOn - 1) * this.pageSize.intValue();
		if (offset < 0) {
			offset = 0;
		}
		RowBounds pageInfo = new RowBounds(offset, this.pageSize.intValue());
		return pageInfo;
	}
}
