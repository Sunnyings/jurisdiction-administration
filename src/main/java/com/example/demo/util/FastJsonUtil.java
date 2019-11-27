package com.example.demo.util;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class FastJsonUtil {
	
	/**
	 * 将jsonArray转换为List<Bean>
	 * 
	 * @param arr
	 * @param objClass
	 * @return
	 */
	public static List<?> toBeanList(String arr, Class<?> objClass) {
		return JSONArray.parseArray(arr, objClass);
	}
	
	/**
	 * 转换集合类为JSON字符串
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String toJSONArrayString(Collection c) {
		return JSONArray.toJSONString(c);
	}
	
}
