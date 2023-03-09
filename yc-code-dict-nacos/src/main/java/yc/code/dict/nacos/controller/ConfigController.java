package yc.code.dict.nacos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RequiredArgsConstructor
@RefreshScope
public class ConfigController {

    @Value("${app.timeout:10}")
    private Long timeout;

    @GetMapping("/getTimeout")
    public Long getTimeout(){
        return timeout;
    }
}
