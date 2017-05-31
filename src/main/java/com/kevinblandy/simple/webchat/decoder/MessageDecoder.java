package com.kevinblandy.simple.webchat.decoder;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.kevinblandy.simple.webchat.cache.UserCacheProvider;
import com.kevinblandy.simple.webchat.model.ChatGroupMessage;
import com.kevinblandy.simple.webchat.model.ChatMessage;
import com.kevinblandy.simple.webchat.utils.SpringContext;


/**
 * 消息编码器
 * @author	KevinBlandy
 * @version	1.0
 * @date	2017年5月23日 下午2:06:19
 */
public class MessageDecoder implements Decoder.Text<ChatMessage>{
	
	@Autowired
	private UserCacheProvider userCacheProvider;
	
	@Override
	public void init(EndpointConfig endpointConfig) {
		this.userCacheProvider = SpringContext.getBean(UserCacheProvider.class);
	}

	@Override
	public void destroy() {
		
	}
	
	@Override
	public ChatMessage decode(String s)  {
		try {
			ChatMessage message = JSON.parseObject(s,ChatMessage.class);
			switch (message.getType()) {
				case GROUP:{	//群组消息
					ChatGroupMessage chatGroupMessage = JSON.parseObject(s,ChatGroupMessage.class);
					//设置消息发送者
					chatGroupMessage.setFrom(this.userCacheProvider.loadUser(chatGroupMessage.getFrom().getUserId()));
					chatGroupMessage.setSuccess(Boolean.TRUE);
					return chatGroupMessage;
				}
			default:
				//非法消息
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ChatMessage(Boolean.FALSE);
	}

	@Override
	public boolean willDecode(String s) {
		return true;
	}
}
