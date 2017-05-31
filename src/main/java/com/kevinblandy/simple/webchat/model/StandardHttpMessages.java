package com.kevinblandy.simple.webchat.model;
/**
 * 预定义Message
 * @author Kevin
 *
 */
public interface StandardHttpMessages {
	/**
	 * 登录失效
	 */
	HttpMessage<Void> SESSION_OUT = new HttpMessage<>(Boolean.FALSE,"登录失效",null,HttpMessage.State.SESSION_OUT);
	
	/**
	 * 操作成功
	 */
	HttpMessage<Void> SUCCESS = new HttpMessage<>(Boolean.TRUE,"ok",null,HttpMessage.State.SUCCESS);
	
	/**
	 * 登录失败
	 */
	HttpMessage<Void> LOGIN_FAILD = new HttpMessage<>(Boolean.FALSE,"登录失败,用户名或者密码错误",null,HttpMessage.State.LOGIN_FAILD);
	
	/**
	 * 验证码错误
	 */
	HttpMessage<Void> VERIFY_CODE_ERROR = new HttpMessage<Void>(Boolean.FALSE,"验证码错误",null,HttpMessage.State.VERIFY_CODE_ERROR);

	/**
	 * 请求参数校验失败
	 */
	HttpMessage<Void> BAD_REQUEST = new HttpMessage<>(Boolean.FALSE,"参数校验失败",null,HttpMessage.State.BAD_REQUEST);
	
	/**
	 * 请求异常 
	 */
	HttpMessage<Void> REQUEST_ERROR = new HttpMessage<>(Boolean.FALSE,"请求错误",null,HttpMessage.State.REQUEST_ERROR);
	
	/**
	 * 请求方法不支持 
	 */
	HttpMessage<Void> METHOD_NOT_SUPPORT = new HttpMessage<>(Boolean.FALSE,"请求方式不支持",null,HttpMessage.State.METHOD_NOT_SUPPORT);
	
	/**
	 * 系统异常
	 */
	HttpMessage<Void> SYSTEM_ERROR = new HttpMessage<>(Boolean.FALSE,"系统异常",null,HttpMessage.State.SYSTEM_ERROR);
	
	/**
	 * 页面未找到
	 */
	HttpMessage<Void> NOT_FOND = new HttpMessage<>(Boolean.FALSE,"页面未找到",null,HttpMessage.State.NOT_FOND);
}
