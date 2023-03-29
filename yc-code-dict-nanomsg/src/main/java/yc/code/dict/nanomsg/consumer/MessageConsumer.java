package yc.code.dict.nanomsg.consumer;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nanomsg.pubsub.SubSocket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * MessageConsumer
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageConsumer {

    private static final SubSocket subSocket = new SubSocket();

    @Value("${nanomsg.topic.testTopic}")
    private String topicUrl;

    @PostConstruct
    @SneakyThrows
    public void start() {
        TimeUnit.SECONDS.sleep(5); //防止生产者启动太快订阅失败
        new Thread(() -> {
            System.out.println("消费者启动");
            subSocket.connect(topicUrl);
            subSocket.subscribe(""); //全局订阅
            for (; ; ) {
                try {
                    String msg = subSocket.recvString();
                    System.out.println(String.format("消费者收到msg：%s", msg));
                } catch (Exception ignore) {
                    //忽略超时
                }
            }
        }, "nanomsg consumer thread").start();
    }
}
