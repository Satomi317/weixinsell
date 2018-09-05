package com.bitekeji.weixinsell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author yuisama
 * @date 2018/8/27 17:16
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {
    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        log.info("【websocket消息】有新的连接，总数为{}",webSocketSet.size());
    }
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        log.info("【websocket消息】连接断开，总数为{}",webSocketSet.size());
    }
    @OnMessage
    public void onMessage(String msg) {
        log.info("【websocket消息】收到客户端发来的信息:{}",msg);
    }
    public void sendMessage(String msg) {
        for (WebSocket webSocket : webSocketSet) {
            log.info("【websocket消息】广播消息,msg={}",msg);
            try {
                webSocket.session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
