package com.kevinblandy.simple.webchat.model;

import com.kevinblandy.simple.webchat.entity.UserEntity;
import com.kevinblandy.simple.webchat.enums.MessageType;

/**
 * 群聊信息
 * @author	KevinBlandy
 * @version	1.0
 * @date	2017年5月24日 下午5:13:45
 */
public class ChatGroupMessage extends ChatMessage{

	private static final long serialVersionUID = 1781590820669748767L;

	private UserEntity from;
	
	//private UserEntity to;
	
	private String content;
	
	
	//constructor
	public ChatGroupMessage(){}
	
	public ChatGroupMessage(UserEntity from){
		super(Boolean.TRUE,MessageType.GROUP);
		this.from = from;
	}
	
	public UserEntity getFrom() {
		return from;
	}

	public void setFrom(UserEntity from) {
		this.from = from;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
