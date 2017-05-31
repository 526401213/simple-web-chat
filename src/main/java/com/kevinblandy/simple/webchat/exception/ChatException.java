package com.kevinblandy.simple.webchat.exception;

import com.kevinblandy.simple.webchat.model.HttpMessage;

/**
 * ChatException
 * @author Kevin
 *
 */
public class ChatException extends Exception{

	private static final long serialVersionUID = 58089824617564272L;

	private HttpMessage.State state;
	
	public ChatException() {}
	
	public ChatException(String message) {
		super(message);
	}
	
	public ChatException(String message,HttpMessage.State state){
		super(message);
		this.state = state;
	}

	public HttpMessage.State getState() {
		return state;
	}

	public void setState(HttpMessage.State state) {
		this.state = state;
	}
	
	public HttpMessage<Void> getErrorMessage(){
		return new HttpMessage<>(Boolean.FALSE,this.getMessage(),null,this.state);
	}
}
