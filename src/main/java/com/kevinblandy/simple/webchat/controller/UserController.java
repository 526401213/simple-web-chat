package com.kevinblandy.simple.webchat.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kevinblandy.simple.webchat.base.BaseController;
import com.kevinblandy.simple.webchat.cache.RedisService;
import com.kevinblandy.simple.webchat.code.RedisKey;
import com.kevinblandy.simple.webchat.code.SessionCode;
import com.kevinblandy.simple.webchat.endpoint.SimpleWebChatEndpoint;
import com.kevinblandy.simple.webchat.entity.UserEntity;
import com.kevinblandy.simple.webchat.enums.MessageType;
import com.kevinblandy.simple.webchat.exception.ChatException;
import com.kevinblandy.simple.webchat.model.ChatSingleUserEventMessage;
import com.kevinblandy.simple.webchat.model.HttpMessage;
import com.kevinblandy.simple.webchat.service.UserService;
import com.kevinblandy.simple.webchat.utils.JsonUtils;
/**
 * 
 * @author Kevin
 *
 */
@Controller
@RequestMapping(value = "user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisService redisService;
	
	/**
	 * 更新个人资料
	 * @param request
	 * @param userId
	 * @param name
	 * @param portrait
	 * @param personality
	 * @param pass
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "update")
	@ResponseBody
	public HttpMessage<UserEntity> update(HttpServletRequest request,
			@RequestParam(value = "userId",required = true) String userId,
			@RequestParam(value = "name",required = true) String name,
			@RequestParam(value = "portrait",required = true) String portrait,
			@RequestParam(value = "personality",required = true) String personality,
			@RequestParam(value = "pass",required = true) String pass) throws Exception{
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(super.getCurrentUser(request).getUserId());
		userEntity.setName(name);
		userEntity.setPortrait(portrait);
		userEntity.setPersonality(personality);
		userEntity.setPass(pass);
		try {
			userEntity = this.userService.updateUser(userEntity);
		}catch(ChatException exception) {
			exception.printStackTrace();
			return (HttpMessage<UserEntity>) super.getErrorMessage(exception.getMessage(), exception.getState());
		}
		if(userEntity == null) {
			return (HttpMessage<UserEntity>) super.getErrorMessage("非法操作", HttpMessage.State.NOT_FOND);
		}
		//session更新
		request.getSession().setAttribute(SessionCode.USER,userEntity);
		userEntity.setCreateDate(null);
		userEntity.setLastLogin(null);
		userEntity.setRemark(null);
		userEntity.setLoginCount(null);
		final UserEntity finalUserEntity = userEntity;
		//redis更新
		this.redisService.set(RedisKey.getUserKey(userEntity.getUserId()), JsonUtils.beanToJson(userEntity));
		//广播更新消息
//    	SimpleWebChatEndpoint.ONLINES.values().parallelStream().forEach(session -> {
//    		if(session.isOpen()) {
//    			try {
//					session.getBasicRemote().sendObject(new ChatSingleUserEventMessage(finalUserEntity,MessageType.USER_INFO_UPDATE));
//				} catch (IOException | EncodeException e) {
//					e.printStackTrace();
//				}
//    		}
//    	});
    	SimpleWebChatEndpoint.radio(new ChatSingleUserEventMessage(finalUserEntity,MessageType.USER_INFO_UPDATE), (session) -> {
    		return Boolean.TRUE;
    	});
		return (HttpMessage<UserEntity>) super.getSuccessMessage(userEntity);
	}
	
}
