package com.yc.code.dict.websocket.server.ws;

import lombok.extern.slf4j.Slf4j;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * LivePlayEndPoint
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
@ServerEndpoint(value = "/ws/live")
@Slf4j
public class LivePlayEndPoint {

    public static void killById(String id) throws IOException {
        Session session = sessionMap.get(id);
        session.close();
        log.info("close session:{}", session.getId());
    }

    /**
     * 保存所有在线socket连接
     */
    private static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    private static final AtomicInteger count = new AtomicInteger(0);


    @OnOpen
    public void onOpen(Session session) {
        sessionMap.put(session.getId(), session);
        addCount();
        log.info("open session：{}， total:{}", session.getId(), getCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("receive: {}：{}", session.getId(), message);
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("send error:", e);
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Throwable error, Session session) {
        log.info("error:{},{}", session.getId(), error.getMessage());
    }

    @OnClose
    public void onClose(Session session) {
        sessionMap.remove(session.getId());
        reduceCount();
        log.info("close session:{}", session.getId());
    }


    public static void broadcast(String text) {
        Iterator<Map.Entry<String, Session>> iterator = sessionMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Session session = iterator.next().getValue();
            if (session.isOpen()) {
                try {
                    synchronized (session) {
                        session.getBasicRemote().sendText(text);
                    }
                } catch (Exception e) {
                    //log.error("send error:", e);
                }
            } else {
                iterator.remove();
                reduceCount();
            }
        }
    }

    /**
     * 获取在线连接数目
     */
    public static int getCount() {
        return count.get();
    }

    public static void addCount() {
        count.incrementAndGet();
    }

    public static void reduceCount() {
        count.decrementAndGet();
    }

}
