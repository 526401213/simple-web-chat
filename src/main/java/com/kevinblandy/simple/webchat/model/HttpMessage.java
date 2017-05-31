package com.kevinblandy.simple.webchat.model;


public class HttpMessage<T> extends Message{

	private static final long serialVersionUID = -749839176022039032L;
	
    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    T data;

    /**
     * 状态
     */
    private State state;


    public HttpMessage(){}

    public HttpMessage(Boolean success, String message, T data, State state){
        super.setSuccess(success);
        this.message = message;
        this.data = data;
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    /**
     * 状态枚举
     */
    public enum State{
        /**
         * 操作成功
         */
        SUCCESS,
        
        /**
         * 系统异常
         */
        SYSTEM_ERROR,
        
        /**
         * 会话失效
         */
        SESSION_OUT,
        
        /**
         * 登录失败
         */
        LOGIN_FAILD,
        
        /**
         * 验证码错误
         */
        VERIFY_CODE_ERROR,
        
        /**
         * 数据不存在
         */
        NOT_FOND,
        
        /**
         * 数据已经存在
         */
        ALREADY_EXISTS,
        
        /**
         * 请求地址错误
         */
        REQUEST_ERROR,
        
        /**
         * 请求方法不支持
         */
        METHOD_NOT_SUPPORT,
        
        /**
         * 参数校验失败
         */
        BAD_REQUEST,
        
        /**
         * 账户被禁用
         */
        ACCOUNT_DISABLED,
        
        /**
         * 没有权限
         */
        NO_PERMISSION,
    }
}
