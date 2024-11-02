package com.sky.Websocket;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/ws/{sid}")
@Slf4j
public class WebsocketServer {
    private static final Map<String, Session> seesionMap = new ConcurrentHashMap<>();

    public void sendToAllClient(String message) {
        for (Session session : seesionMap.values()) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        log.info("客户端+{}+建立连接", sid);
        seesionMap.put(sid, session);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("sid") String sid) {
        log.info("客户端{}发送消息,{}", sid, message);
    }

    @OnClose
    public void onClose(@PathParam("sid") String sid) {
        log.info("客户端{}断开连接", sid);
        seesionMap.remove(sid);
    }
}
