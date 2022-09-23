package com.syes.syes_springboot.component;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@ServerEndpoint(value = "/chatServer/{id}")
public class WebSocketServer {
    Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    //记录当前连接客户端
    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    /*
     * 当链接建立时，自动建立该方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        //未收到参数，直接关闭session
        if (id == null || "".equals(id)) {
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        //当前在线中已存在，关闭旧连接
        sessionMap.forEach((userId, userSession) -> {
            if (userId.equals(id)) {
                try {
                    userSession.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        sessionMap.put(id, session);
        logger.info("有新的用户加入聊天，当前用户id：{}，总人数为：{}", id, sessionMap.size());
    }

    /*
     * 连接关闭
     * */
    @OnClose
    public void onClose(Session session, @PathParam("id") String id) {
        sessionMap.remove(id);
        logger.info("{}关闭了连接，当前在线人数为:{}", id, sessionMap.size());
    }

    /*
     *接受前端发来的消息
     * */
    @OnMessage
    public void onMessage(Session session, @PathParam("id") String id, String message) {
        logger.info("来自id:{}的消息：{}", id, message);
        //拆解消息
        JSONObject obj = JSONUtil.parseObj(message);
        //发送给哪个用户的消息
        String toUserId = obj.getStr("toUserId");
        //发送消息的内容
        String text = obj.getStr("context");
    }

    /**
     * 服务端发送消息给客户端
     */
    private void sendMessage(String message, Session toSession) {
        try {
            logger.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
            toSession.getBasicRemote().sendText(message);
        } catch (Exception e) {
            logger.error("服务端发送消息给客户端失败", e);
        }
    }

    /**
     * 服务端发送消息给所有客户端
     */
    private void sendAllMessage(String message) {
        try {
            for (Session session : sessionMap.values()) {
                logger.info("服务端给客户端[{}]发送消息{}", session.getId(), message);
                session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            logger.error("服务端发送消息给客户端失败", e);
        }
    }
}

