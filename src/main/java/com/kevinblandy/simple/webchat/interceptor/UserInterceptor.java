package com.kevinblandy.simple.webchat.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kevinblandy.simple.webchat.annotation.NoLogin;
import com.kevinblandy.simple.webchat.base.BaseInterceptor;
import com.kevinblandy.simple.webchat.code.SessionCode;
import com.kevinblandy.simple.webchat.model.HttpMessage;
import com.kevinblandy.simple.webchat.model.StandardHttpMessages;
/**
 * 用户拦截器
 * @author Kevin
 */
public class UserInterceptor extends BaseInterceptor<HttpMessage<Void>>{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserInterceptor.class);
	
	@Override
	public boolean doPreHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,Method method) throws Exception {
		if(method.isAnnotationPresent(NoLogin.class)) {
			return true;
		}
		LOGGER.debug("interceptor={}",this.getClass().getName());
		Object obj = httpServletRequest.getSession().getAttribute(SessionCode.USER);
		if(obj == null) {
			LOGGER.debug("interceptor = 未登录");
			super.response(httpServletRequest, httpServletResponse, StandardHttpMessages.SESSION_OUT, "/login");
			return false;
		}
		return true;
	}
}
