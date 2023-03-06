package com.yc.code.dict.websocket.client.consumer;

import org.springframework.stereotype.Service;

/**
 * ConsumerService
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
@Service
public class ConsumerService {

    public void onMessage(String text) {
        System.out.println(text);
    }
}
