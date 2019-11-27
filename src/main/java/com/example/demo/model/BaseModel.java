package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

public class BaseModel implements Serializable, Cloneable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	private Boolean isDeleted = false;

	private Date createDate;

	private Date updateDate = new Date();

	private String operator;

	private Date now = new Date();

	private Integer page;

	private Integer limit;

	private Integer total;

	private String param;

	/*
	 * layui.table 字段名
	 */
	private String field;

	/*
	 * 排序方式：asc/desc
	 */
	private String order;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Date getNow() {
		return now;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

}
