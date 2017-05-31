package com.kevinblandy.simple.webchat.encoder;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.kevinblandy.simple.webchat.utils.JsonUtils;
/**
 * 消息解码器
 * @author	KevinBlandy
 * @version	1.0
 * @date	2017年5月23日 下午2:07:13
 */
public class MessageEncoder implements Encoder.Text<Object>{

	@Override
	public void init(EndpointConfig endpointConfig) {
		
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public String encode(Object object) throws EncodeException {
		return JsonUtils.beanToJson(object);
	}
}
