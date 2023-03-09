package yc.code.dict.spring.spi.hello;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hello")
@Getter
@Setter
public class MainProperties {
    private String keyName;
}
