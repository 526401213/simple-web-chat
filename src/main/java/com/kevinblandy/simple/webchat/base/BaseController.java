package com.kevinblandy.simple.webchat.base;

import javax.servlet.http.HttpServletRequest;

import com.kevinblandy.simple.webchat.code.SessionCode;
import com.kevinblandy.simple.webchat.entity.UserEntity;
import com.kevinblandy.simple.webchat.model.HttpMessage;

public class BaseController {
	
	/**
	 * @param data
	 * @return
	 */
	public <T> HttpMessage<?> getSuccessMessage(T data){
		return new HttpMessage<Object>(Boolean.TRUE,"ok",data,HttpMessage.State.SUCCESS);
	}
	
	/**
	 * @param message
	 * @param state
	 * @return
	 */
	public <T> HttpMessage<?> getErrorMessage(String message,HttpMessage.State state){
		return new HttpMessage<Object>(Boolean.FALSE,message,null,state);
	}
	
	/**
	 * @param request
	 * @return
	 */
	public UserEntity getCurrentUser(HttpServletRequest request) {
		return (UserEntity) request.getSession().getAttribute(SessionCode.USER);
	}
}
