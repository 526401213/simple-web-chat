package com.kevinblandy.simple.webchat.model;

import com.kevinblandy.simple.webchat.entity.UserEntity;
import com.kevinblandy.simple.webchat.enums.MessageType;

/**
 * 单个用户事件消息
 * @author	KevinBlandy
 * @version	1.0
 * @date	2017年5月24日 下午5:21:42
 */
public class ChatSingleUserEventMessage extends ChatMessage{

	private static final long serialVersionUID = -7308559517917474648L;
	
	private UserEntity user;

	//constructor
	public ChatSingleUserEventMessage(){}
	
	public ChatSingleUserEventMessage(UserEntity user,MessageType type){
		super(Boolean.TRUE,type);
		this.user = user;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}
