package com.kh.icodi.ws.config;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import com.kh.icodi.member.model.dto.Member;

public class HelloWebSocketConfigurator extends Configurator{
	
	
	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		HttpSession httpSession = (HttpSession)request.getHttpSession();
		Member loginMember = (Member)httpSession.getAttribute("loginMember");
		String memberId = loginMember.getMemberId();
		
		Map<String, Object> userProperties = sec.getUserProperties();
		userProperties.put("memberId", memberId);
		System.out.println("[helloWebSocketConfigurator#modifyHandShake] memberId= " + memberId);
		
		
		
	}
}
