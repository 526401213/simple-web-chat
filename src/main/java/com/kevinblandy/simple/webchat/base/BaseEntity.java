package com.kevinblandy.simple.webchat.base;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.kevinblandy.simple.webchat.enums.State;
/**
 * BaseEntity
 * @author	KevinBlandy
 * @version	1.0
 * @date	2017年5月21日 下午3:14:33
 */
public class BaseEntity implements Serializable{
	
	private static final long serialVersionUID = -9091696845998865545L;
	
	private State state;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;
	
	private String remark;

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "BaseEntity [state=" + state + ", createDate=" + createDate + ", remark=" + remark + "]";
	}
}
