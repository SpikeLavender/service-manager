package com.chinamobile.demo.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author hetengjiao@chinamobile.com
 * @date 2019/8/20
 *
 */
public class CommonUtil {

	/**
	 * 获得一个UUID
	 * @return String UUID
	 */
	public static String getUUID(){
		String uuid = UUID.randomUUID().toString();
		//去掉“-”符号
		return uuid.replaceAll("-", "");
	}

	public static JSONObject objectToJsonObj(Object object){
		return (JSONObject) JSONObject.toJSON(object);
	}

	public static JSONArray objectToJsonArr(Object object){
		return (JSONArray) JSONArray.toJSON(object);
	}

}
