package com.kevinblandy.simple.webchat.utils;

import java.util.Base64;

/**
 * 文件工具类
 * @author	KevinBlandy
 * @version	1.0
 * @date	2017年5月22日 下午12:47:20
 */
public class FileUtils {
	
	private FileUtils(){}

	/**
	 * 图片转换为Base64编码
	 * @param bytes
	 * @return
	 */
	public static String fileToBase64(byte[] bytes){
		return Base64.getEncoder().encodeToString(bytes);
	}
}
