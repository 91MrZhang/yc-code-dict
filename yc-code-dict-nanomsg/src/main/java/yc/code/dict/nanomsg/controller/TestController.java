package yc.code.dict.nanomsg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yc.code.dict.nanomsg.producer.MessageProducer;

/**
 * TestController
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/produceMessage")
    public void produceMessage() {
        MessageProducer.send(String.valueOf(System.currentTimeMillis()));
    }

}
