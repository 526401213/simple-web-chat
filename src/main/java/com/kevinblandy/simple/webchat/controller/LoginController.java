package com.kevinblandy.simple.webchat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kevinblandy.simple.webchat.cache.RedisService;
import com.kevinblandy.simple.webchat.code.RedisKey;
import com.kevinblandy.simple.webchat.code.SessionCode;
import com.kevinblandy.simple.webchat.entity.UserEntity;
import com.kevinblandy.simple.webchat.model.HttpMessage;
import com.kevinblandy.simple.webchat.model.StandardHttpMessages;
import com.kevinblandy.simple.webchat.service.UserService;
import com.kevinblandy.simple.webchat.utils.JsonUtils;
import com.kevinblandy.simple.webchat.views.StandardViews;

@Controller
@RequestMapping(value = "login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	@Autowired
	private RedisService redisService;
	
	/**
	 * 登录页面
	 * @return
	 */
	@GetMapping
	public ModelAndView login() {
		return StandardViews.LOGIN;
	}
	
	/**
	 * 执行登录
	 * @param request
	 * @param name
	 * @param pass
	 * @param verifyCode
	 * @return
	 * @throws Exception
	 */
	@PostMapping
	@ResponseBody
	public HttpMessage<Void> login(HttpServletRequest request,
			@RequestParam(value = "name",required = true) String name,
			@RequestParam(value = "pass",required = true) String pass,
			@RequestParam(value = "verifyCode",required = true)String verifyCode)throws Exception{
		if(!verifyCode.equalsIgnoreCase((String)request.getSession().getAttribute(SessionCode.VERIFY_CODE))){
			return StandardHttpMessages.VERIFY_CODE_ERROR;
		}
		UserEntity userEntity = this.userService.login(name,pass);
		if(userEntity == null){
			return StandardHttpMessages.LOGIN_FAILD;
		}
		request.getSession().setAttribute(SessionCode.USER, userEntity);
		this.redisService.set(RedisKey.getUserKey(userEntity.getUserId()), JsonUtils.beanToJson(userEntity));
		this.threadPoolTaskExecutor.execute(() -> {
			try {
				this.userService.refreshTheLogin(userEntity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return StandardHttpMessages.SUCCESS;
	}
}
