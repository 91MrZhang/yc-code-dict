package yc.code.dict.nanomsg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * AppApplication
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
@SpringBootApplication
public class AppApplication {


    public static void main(String[] args) {
        /*
         * 这里要注意平台的区别，windows用dll、lib文件，linux用so文件
         */
        System.load("D:\\zyt\\mrzhang\\yc-code-dict\\yc-code-dict-nanomsg\\src\\main\\libs\\nanomsg.dll");
        //System.load("D:\\zyt\\mrzhang\\yc-code-dict\\yc-code-dict-nanomsg\\src\\main\\libs\\libnanomsg.so");
        //System.load("D:\\zyt\\mrzhang\\yc-code-dict\\yc-code-dict-nanomsg\\src\\main\\libs\\nanomsg.lib");
        SpringApplication.run(AppApplication.class,args);
    }
}

