package com.kevinblandy.simple.webchat.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 消息
 * @author	KevinBlandy
 * @version	1.0
 * @date	2017年5月20日 下午10:13:44
 */
public abstract class Message implements Serializable{

	private static final long serialVersionUID = 9042896593197045672L;
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date date;
	
	private Boolean success;

	public Date getDate() {
		return date == null ? new Date() : this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "Message{" +
				"date=" + date +
				", success=" + success +
				'}';
	}
}
