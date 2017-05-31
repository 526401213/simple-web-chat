package com.kevinblandy.simple.webchat.configurator;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;
/**
 * ws握手
 * @author Kevin
 */
public class SimpleWebChatConfigurator extends Configurator{
	
	public static String HTTP_SESSION = "HTTP_SESSION";
	
	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		//HttpSession
		sec.getUserProperties().put(HTTP_SESSION,request.getHttpSession());
		super.modifyHandshake(sec,request,response);
	}
}
