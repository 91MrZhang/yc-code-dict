package yc.code.dict.nanomsg.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nanomsg.pubsub.PubSocket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * MessageProducer
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageProducer {

    private static final PubSocket pubSocket = new PubSocket();

    @Value("${nanomsg.topic.testTopic}")
    private String topicUrl;

    @PostConstruct
    private void init() {
        pubSocket.bind(topicUrl);
    }

    public static void send(String str) {
        pubSocket.send(str);
    }

}
