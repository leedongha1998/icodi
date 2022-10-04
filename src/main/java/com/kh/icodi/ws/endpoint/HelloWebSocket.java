package com.kh.icodi.ws.endpoint;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.kh.icodi.ws.config.HelloWebSocketConfigurator;

@ServerEndpoint(value = "/helloWebSocket", configurator = HelloWebSocketConfigurator.class)
public class HelloWebSocket {
	
	private static Map<String, Session> clientMap = Collections.synchronizedMap(new HashMap<>());
	
	@OnOpen
	public void onOpen(EndpointConfig config, Session session) {
		Map<String, Object> userProperties = config.getUserProperties();
		String memberId = (String)userProperties.get("memberId");
		System.out.println("[@OnOpen] memberId= " + memberId);
		
		addToClientMap(memberId, session);
		
		Map<String, Object> sessionUserProperties = session.getUserProperties();
		sessionUserProperties.put("memberId", memberId);
	}
	
	

	@OnMessage
	public void onMessage(String msg, Session session) {
		System.out.println(msg);
		Collection<Session> sessions =  clientMap.values();
		for(Session each : sessions) {
			Basic basic = each.getBasicRemote();
			try {
				basic.sendText(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@OnError
	public void onError(Throwable e) {
		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session session) {
		Map<String, Object> sessionUserProperties = session.getUserProperties();
		String memberId = (String) sessionUserProperties.get("memberId");
		removeFromClientMap(memberId);
	}
	
	private static void removeFromClientMap(String memberId) {
		clientMap.remove(memberId);
		log();
	}



	private static void addToClientMap(String memberId, Session session) {
		clientMap.put(memberId, session);
		log();
	}
	
	private static void log() {
		System.out.println("[HelloWebSocekt Log] 현재 접속자 수 : " + clientMap.size() + " " + clientMap.keySet());
	}



	public static boolean isConnected(String receiver) {
		return clientMap.containsKey(receiver);
	}



	public static void sendMessage(MessageType messageType, Map<String, Object> data) {
		String receiver = (String) data.get("receiver");
		Session session = clientMap.get(receiver);
		if(session != null) {
			Basic basic = session.getBasicRemote();
			try {
				basic.sendText(msgToJson(messageType,data));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



	private static String msgToJson(MessageType messageType, Map<String, Object> data) {
		Map<String, Object> map = new HashMap<>();
		map.put("messageType", messageType);
		map.put("data", data);
		map.put("time", System.currentTimeMillis());
		
		return new Gson().toJson(map);
	}
}
