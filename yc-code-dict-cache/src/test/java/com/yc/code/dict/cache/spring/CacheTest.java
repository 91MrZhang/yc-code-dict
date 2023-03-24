package com.yc.code.dict.cache.spring;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheTest {

    @Autowired
    private LaunchService launchService;

    @Test
    @SneakyThrows
    public void test() {
        launchService.getMonitorRecords("1");
        launchService.getMonitorRecords("1");
        launchService.getMonitorRecords("2");
        TimeUnit.SECONDS.sleep(12);
        launchService.getMonitorRecords("1");
    }
}
