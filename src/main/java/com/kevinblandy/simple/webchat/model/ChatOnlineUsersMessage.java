package com.kevinblandy.simple.webchat.model;

import java.util.List;

import com.kevinblandy.simple.webchat.entity.UserEntity;
import com.kevinblandy.simple.webchat.enums.MessageType;

/**
 * 在线人数信息
 * @author	KevinBlandy
 * @version	1.0
 * @date	2017年5月24日 下午5:33:04
 */
public class ChatOnlineUsersMessage extends ChatMessage{

	private static final long serialVersionUID = 892385574101326403L;
	
	private List<UserEntity> users;
	
	public ChatOnlineUsersMessage(){}
	
	public ChatOnlineUsersMessage(List<UserEntity> users){
		super(Boolean.TRUE,MessageType.ONLINE);
		this.users = users;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
}
