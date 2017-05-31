package com.kevinblandy.simple.webchat.code;

public interface RedisKey {
	
	String USER = "user_";
	
	String ONLINE_USERS = "ONLINE_USERS";
	
	/**
	 * @param userId
	 * @return
	 */
	static String getUserKey(String userId) {
		return USER + userId;
	}
}