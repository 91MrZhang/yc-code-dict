package com.yc.code.dict.spring.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * AppApplication
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
@SpringBootApplication
@EnableSwagger2
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class,args);
    }
}
