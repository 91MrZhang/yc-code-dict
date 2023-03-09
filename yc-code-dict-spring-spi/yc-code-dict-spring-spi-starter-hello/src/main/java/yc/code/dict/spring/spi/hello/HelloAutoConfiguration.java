package yc.code.dict.spring.spi.hello;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 一般情况下用spring.factories用EnableAutoConfiguration的比较多
 * 可以添加多个AutoConfiguration
 *
 * 其余类型可以参考spring-boot的spring.factories
 *
 * 其中有一些加载顺序的注解在实际使用中比较常用，可以参考springAPI
 * 例如
 * AutoConfigureAfter
 * AutoConfigureBefor
 * Conditional
 *
 */
@Configuration
@EnableConfigurationProperties(MainProperties.class)
public class HelloAutoConfiguration {

    @Resource
    private MainProperties mainProperties;


    @PostConstruct
    public void init() {
        System.out.println("starter依赖--> " +mainProperties.getKeyName() + "-->来自HelloAutoConfiguration");
    }
}
