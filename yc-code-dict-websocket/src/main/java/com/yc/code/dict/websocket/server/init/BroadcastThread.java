package com.yc.code.dict.websocket.server.init;

import com.yc.code.dict.websocket.server.ws.LivePlayEndPoint;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * BroadcastThread
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
@Component
public class BroadcastThread {

    @PostConstruct
    public void init(){
        System.out.println("ws server start");
        new Thread(()-> {
            try {
                for(;;) {
                    //每隔1000毫秒 广播当前时间戳
                    LivePlayEndPoint.broadcast(String.valueOf(System.currentTimeMillis()));
                    Thread.sleep(1000L);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"websocket-server thread").start();
    }
}
