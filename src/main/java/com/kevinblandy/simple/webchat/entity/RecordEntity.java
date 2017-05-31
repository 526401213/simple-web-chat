package com.kevinblandy.simple.webchat.entity;

import com.kevinblandy.simple.webchat.base.BaseEntity;

public class RecordEntity extends BaseEntity{
	
	private static final long serialVersionUID = 917193238253224524L;

	private String recordId;		
	
	private String userId;			
	
	private String content;

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
