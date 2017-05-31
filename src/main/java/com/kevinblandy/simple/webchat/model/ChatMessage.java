package com.kevinblandy.simple.webchat.model;

import com.kevinblandy.simple.webchat.enums.MessageType;

/**
 * 聊天信息
 * @author	KevinBlandy
 * @version	1.0
 * @date	2017年5月23日 下午1:44:21
 */
public class ChatMessage extends Message{

	private static final long serialVersionUID = 6971870404730836728L;
	
	private MessageType type;
	
	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	//constructor
	public ChatMessage() {}
	
	public ChatMessage(Boolean success) {
		super.setSuccess(success);
	}
	
	public ChatMessage(MessageType type) {
		this.type = type;
	}
	
	public ChatMessage(Boolean success,MessageType type){
		super.setSuccess(success);
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "ChatMessage [type=" + type + "]";
	}
}
