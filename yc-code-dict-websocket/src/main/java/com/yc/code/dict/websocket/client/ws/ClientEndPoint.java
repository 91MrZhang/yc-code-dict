package com.yc.code.dict.websocket.client.ws;

import com.yc.code.dict.websocket.client.consumer.ConsumerService;
import com.yc.code.dict.websocket.utils.SpringContextUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.*;

/**
 * ClientEndPoint
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
@Getter
@Setter
@Slf4j
public class ClientEndPoint extends WebSocketClient {

    private String url;

    private ConsumerService consumerService;

    public ClientEndPoint(String url) throws URISyntaxException {
        super(new URI(url));
        this.url = url;
        getConsumerService();
    }


    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        log.debug("websocket client open : {}", url);
    }

    @Override
    public void onMessage(String message) {
        //业务消费者
        consumerService.onMessage(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.debug("onClose code:{} reason:{} remote:{}",code,reason,remote);
    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
    }


    private ConsumerService getConsumerService() {
        if (consumerService == null) {
            consumerService = SpringContextUtil.getBean(ConsumerService.class);
        }
        return consumerService;
    }

    ExecutorService singleThreadPool = Executors.newFixedThreadPool(1);

    public void startConnectListener() {
        new Thread(() -> {
            System.out.println("connect listener thread start");
            for (; ; ) {
                try {
                    TimeUnit.SECONDS.sleep(5); //每隔5s重连一次
                    if (!this.isOpen()) {
                         singleThreadPool.submit(new Thread(() -> {
                            System.out.println("ws client connect thread start");
                            try {
                                if (this.getReadyState().equals(ReadyState.NOT_YET_CONNECTED)) {
                                    this.connectBlocking();
                                } else if (this.getReadyState().equals(ReadyState.CLOSING) || this.getReadyState().equals(ReadyState.CLOSED)) {
                                    this.reconnectBlocking();
                                }
                            } catch (Exception e) {
                                //e.printStackTrace();
                            }
                        }, "ws client connect thread"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "connect listener thread").start();
    }
}
