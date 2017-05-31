package com.kevinblandy.simple.webchat.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.kevinblandy.simple.webchat.base.BaseEntity;
import com.kevinblandy.simple.webchat.enums.Role;
/**
 * 用户
 * @author	KevinBlandy
 * @version	1.0
 * @date	2017年5月21日 下午3:41:31
 */
public class UserEntity extends BaseEntity{

	private static final long serialVersionUID = -4562944958147232726L;
	
	private String userId;		//用户ID

    @JSONField(serialize = false)
	private String sessionId;	//会话ID
    
	private String name;		//昵称/登录账户名
	
	@JSONField(serialize = false)
	private String pass;		//密码
	
	private String portrait;	//头像

	private String personality;	//个性签名
	
	private Role role;			//角色
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastLogin;		//最后一次登录
	
	private Integer loginCount;	//登录次数
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPortrait() {
		return portrait;
	}
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
    public String getSessionId() {
        return sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
	public String getPersonality() {
		return personality;
	}
	public void setPersonality(String personality) {
		this.personality = personality;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public Integer getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
}
