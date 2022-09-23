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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/chatServer/{id}")
public class WebSocketServer {
    Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    //记录当前在线列表
    //ConcurrentHashMap专用于多线程，是线程安全的
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
        //将用户加入到聊天列表
        sessionMap.put(id, session);
        logger.info("有新的用户加入聊天，当前用户id：{}，总人数为：{}", id, sessionMap.size());
    }

    /*
     * 连接关闭
     * */
    @OnClose
    public void onClose(Session session, @PathParam("id") String id) {
        //将用户从列表中删除
        sessionMap.remove(id);
        logger.info("{}关闭了连接，当前在线人数为:{}", id, sessionMap.size());
    }

    /*
     *接受前端发来的消息
     *
     * message已经默认发来的消息了，后端能成功接受，都不是问题
     * */
    @OnMessage
    public void onMessage(Session session, @PathParam("id") String id, String message) throws IOException {
        //发来的消息
        JSONObject obj = JSONUtil.parseObj(message);
        String toUserId = obj.getStr("toUserId");
        String context = obj.getStr("context");
        //要发出去的消息
        HashMap<String, Object> toMessage = new HashMap<>();
        toMessage.put("fromUserId", id);
        toMessage.put("message", context);
        toMessage.put("time", LocalDateTime.now());

        //遍历当前在线列表，有则直接发送消息，不在线就放到消息队列
        if (sessionMap.containsKey(toUserId)) {
            sendMessage(toMessage, sessionMap.get(toUserId));
        } else {
            logger.info("该用户不在线");
        }

    }

    /**
     * 服务端发送消息给客户端
     */
    private void sendMessage(HashMap message, Session toSession) throws IOException {
        String s = JSONUtil.toJsonStr(message);
        toSession.getBasicRemote().sendText(s);
    }


}

