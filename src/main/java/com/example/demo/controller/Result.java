package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import com.example.demo.model.BaseModel;



public class Result<T1 extends BaseModel> {

    private Integer code=0;//默认状0为不处理msg其他值处理msg
    private String msg;
    private Integer count;
    private T1 entity;
    private List<?> data;
    private Map<?,?> map;
    private String uri;//跳转路径

    public Result() {
    	
    }
    
    public Result(List<?> data){
    	this.data=data;
    }
    
    public Result(T1 entity){
    	this.entity=entity;
    }
    
    public Result(List<?> data,Integer count){
    	this.data=data;
    	this.count=count;
    }
    
    public Result(Integer code,String msg){
    	this.code=code;
    	this.msg=msg;
    }
    
    public Result(Map<?,?> map){
    	this.map=map;
    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public T1 getEntity() {
		return entity;
	}

	public void setEntity(T1 entity) {
		this.entity = entity;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public Map<?, ?> getMap() {
		return map;
	}

	public void setMap(Map<?, ?> map) {
		this.map = map;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	
   
}
