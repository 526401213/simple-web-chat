package com.kevinblandy.simple.webchat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kevinblandy.simple.webchat.annotation.NoLogin;
import com.kevinblandy.simple.webchat.cache.RedisService;
import com.kevinblandy.simple.webchat.code.RedisKey;
import com.kevinblandy.simple.webchat.code.SessionCode;
import com.kevinblandy.simple.webchat.entity.UserEntity;
import com.kevinblandy.simple.webchat.exception.ChatException;
import com.kevinblandy.simple.webchat.model.HttpMessage;
import com.kevinblandy.simple.webchat.model.StandardHttpMessages;
import com.kevinblandy.simple.webchat.service.UserService;
import com.kevinblandy.simple.webchat.utils.JsonUtils;

@Controller
@RequestMapping(value = "register")
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisService redisService;
	
	@PostMapping
	@ResponseBody
	@NoLogin
	public HttpMessage<Void> register(HttpServletRequest  httpServletRequest,
			@RequestParam(value = "name",required = true)String name,
			@RequestParam(value = "pass",required = true) String pass,
			@RequestParam(value = "verifyCode",required = true)String verifyCode)throws Exception{
		if(!verifyCode.equalsIgnoreCase((String) httpServletRequest.getSession().getAttribute(SessionCode.VERIFY_CODE))){
			return StandardHttpMessages.VERIFY_CODE_ERROR;
		}
		UserEntity userEntity = null;
		try{
			userEntity = this.userService.register(name,pass);
		}catch(ChatException e){
			return e.getErrorMessage(); 
		}
		httpServletRequest.getSession().setAttribute(SessionCode.USER, userEntity);
		this.redisService.set(RedisKey.getUserKey(userEntity.getUserId()), JsonUtils.beanToJson(userEntity));
		return StandardHttpMessages.SUCCESS;
	}
}

