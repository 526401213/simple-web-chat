package com.kevinblandy.simple.webchat.enums;
/**
 * 消息类型
 * @author	KevinBlandy
 * @version	1.0
 * @date	2017年5月20日 下午10:08:57
 */
public enum MessageType {
	
	/**
	 * 群组消息
	 */
	GROUP,
	
	/**
	 * 系统消息
	 */
	SYSTEM,
	
	/**
	 * 在线人数通知消息
	 */
	ONLINE,
	
	/**
	 * 退出消息
	 */
	EXIT,
	
	/**
	 * 加入消息
	 */
	JOIN,

	/**
	 * 个人资料更新
	 */
	USER_INFO_UPDATE,
	
	/**
	 * 另一登录
	 */
	OTHER_LOGIN,
	
	/**
	 * 聊天申请
	 */
	CHAT_APPLY,
	
	/**
	 * 同意
	 */
	CHAT_AGREE,

	/**
	 * 拒绝
	 */
	CHAT_REFUSE,
	
	/**
	 * 聊天断开
	 */
	CHAT_CLOSE,
}
