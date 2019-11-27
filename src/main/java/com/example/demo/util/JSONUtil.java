package com.example.demo.util;

import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * JSON工具类
 * 
 * @author shaozr
 *
 */
@Deprecated
public class JSONUtil {

	/**
	 * JSON字符串转换成对象
	 * 
	 * @param jsonString
	 *            需要转换的字符串
	 * @param type
	 *            需要转换的对象类型
	 * @return 对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String jsonString, Class<T> type) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		return (T) JSONObject.toBean(jsonObject, type);
	}

	/**
	 * 转换object为JSON字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJSONString(Object obj) {
		return JSONObject.fromObject(obj).toString();
	}

	/**
	 * 将jsonArray转换为List<Bean>
	 * 
	 * @param arr
	 * @param objClass
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@Deprecated
	public static List<?> toBeanList(Object arr, Class objClass) {
		return (List<?>) JSONArray.toCollection(JSONArray.fromObject(arr),
				objClass);
		
	}

	/**
	 * 转换集合类为JSON字符串
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String toJSONArrayString(Collection c) {
		return JSONArray.fromObject(c).toString();
	}

}
