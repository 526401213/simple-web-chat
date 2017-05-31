package com.kevinblandy.simple.webchat.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * JsonUtils
 * @author	KevinBlandy
 * @version	1.0
 * @date	2017年5月12日 上午11:39:27
 */
public class JsonUtils {
	/**
	 * 把数据转换为JSON
	 * @param bean
	 * @return
	 */
	public static String beanToJson(Object bean){
		try{
			return JSON.toJSONString(bean,SerializerFeature.PrettyFormat,				//格式化
											//SerializerFeature.WriteMapNullValue,		//输出null字段
											SerializerFeature.QuoteFieldNames			//使用双引号
											//SerializerFeature.WriteNullListAsEmpty	//把null集合/数组输出为[]
											);	
		}catch(Exception e){
			return null;
		}
	}
}
