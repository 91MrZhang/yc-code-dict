package com.yc.code.dict.websocket.client.init;

import com.yc.code.dict.websocket.client.ws.ClientEndPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * ConsumerThread
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
@Component
@RequiredArgsConstructor
@DependsOn(value = "springContextUtil")
public class ConsumerThread {

    @Value("${ws.url}")
    private String url;


    @PostConstruct
    public void initWsConsumer() throws Exception {
        new ClientEndPoint(url).startConnectListener();
    }
}
