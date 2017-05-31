package com.kevinblandy.simple.webchat.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.kevinblandy.simple.webchat.code.RedisKey;
import com.kevinblandy.simple.webchat.entity.UserEntity;
import com.kevinblandy.simple.webchat.exception.ChatException;
import com.kevinblandy.simple.webchat.service.UserService;
import com.kevinblandy.simple.webchat.utils.GeneralUtils;
import com.kevinblandy.simple.webchat.utils.JsonUtils;

@Component
public class UserCacheProvider {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisService redisService;
	
	/**
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UserEntity loadUser(String userId) throws Exception {
		if(GeneralUtils.isEmpty(userId)) {
			throw new ChatException("空值~");
		}
		UserEntity userEntity = null;
		String json = this.redisService.get(RedisKey.getUserKey(userId));
        if(!GeneralUtils.isEmpty(json)) {
        	//命中缓存
        	userEntity = JSON.parseObject(json, UserEntity.class);
        }else {
        	//尝试从数据库检索
        	userEntity = this.userService.queryByPrimaryKey(userId);
        	if(userEntity == null) {
        		throw new ChatException(userId+",用户不存在");
        	}
        	//刷入缓存
        	this.redisService.set(RedisKey.getUserKey(userId), JsonUtils.beanToJson(userEntity));
        }
        return userEntity;
	}
}
