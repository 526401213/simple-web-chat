package com.kevinblandy.simple.webchat.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kevinblandy.simple.webchat.entity.UserEntity;
import com.kevinblandy.simple.webchat.enums.Role;
import com.kevinblandy.simple.webchat.enums.State;
import com.kevinblandy.simple.webchat.exception.ChatException;
import com.kevinblandy.simple.webchat.model.HttpMessage;
import com.kevinblandy.simple.webchat.service.generic.GenericService;
import com.kevinblandy.simple.webchat.utils.GeneralUtils;

@Service
public class UserService extends GenericService<UserEntity>{
	
	@Value(value = "${user.default.portrait}")
	private String portrait;
	
	@Value(value = "${user.default.personality}")
	private String personality;
	
	@Transactional(readOnly = true)
	public UserEntity login(String name, String pass) throws Exception {
		UserEntity userEntity = new UserEntity();
		userEntity.setName(name);
		userEntity.setPass(pass);		//不加密了
		return super.queryByParamSelectiveSole(userEntity);
	}
	
	@Transactional
	public void refreshTheLogin(UserEntity userEntity) throws Exception {
		UserEntity update = new UserEntity();
		update.setUserId(userEntity.getUserId());
		update.setLastLogin(new Date());
		update.setLoginCount(userEntity.getLoginCount() == null ? 1 : userEntity.getLoginCount() + 1);
		super.updateByPrimaryKeySelective(update);
	}
	
	@Transactional
	public UserEntity register(String name, String pass) throws Exception {
		UserEntity userEntity = new UserEntity();
		userEntity.setName(name);
		if(super.queryByParamSelectiveSole(userEntity) != null){
			throw new ChatException("用户名:["+name+"],已经存在",HttpMessage.State.ALREADY_EXISTS);
		}
		userEntity.setPass(pass);
		userEntity.setUserId(GeneralUtils.getUUID());
		userEntity.setCreateDate(new Date());
		userEntity.setRole(Role.USER);
		userEntity.setState(State.ENABLED);
		userEntity.setPersonality(this.personality);
		userEntity.setPortrait(this.portrait);
		userEntity.setLastLogin(new Date());
		userEntity.setLoginCount(1);
		super.create(userEntity);
		return userEntity;
	}
	
	@Transactional
	public UserEntity updateUser(UserEntity entity) throws Exception {
		UserEntity exits = new UserEntity();
		exits.setName(entity.getName());
		exits = super.queryByParamSelectiveSole(entity);
		if(exits != null && !exits.getUserId().equals(entity.getUserId())) {
			throw new ChatException("用户名:["+exits.getName()+"],已经存在",HttpMessage.State.ALREADY_EXISTS);
		}
		super.updateByPrimaryKeySelective(entity);
		return super.queryByPrimaryKey(entity.getUserId());
	}
}
