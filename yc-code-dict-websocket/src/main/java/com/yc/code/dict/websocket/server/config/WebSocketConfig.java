package com.yc.code.dict.websocket.server.config;

import com.yc.code.dict.websocket.server.ws.LivePlayEndPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebSocketConfig
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig {

    @Bean
    public LivePlayEndPoint livePlayEndPoint() { return new LivePlayEndPoint(); }

    /**
     * websocket
     * ServerEndpointExporter必须注入
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
