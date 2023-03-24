package com.yc.code.dict.cache.spring;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

/**
 * CacheConfig
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
@Configuration
@EnableCaching
@Slf4j
public class CacheConfig {

    /**
     * 自定义缓存管理器，包括缓存大小，过期时间
     * @return CacheManager
     */
    @Primary
    @Bean("monitorRecordsCacheMgr")
    public CacheManager monitorRecordsCacheMgr() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .maximumSize(50000);
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }

    /**
     * 自定义缓存键生成策略
     * @return KeyGenerator
     */
    @Primary
    @Bean("monitorRecordsCacheKeyGen")
    public KeyGenerator monitorRecordsCacheKeyGen() {
        return (target, method, params) -> {
            //String s = target.toString()+":"+method.getName()+":"+ params[0].toString();
            String s = method.getName() + ":" + params[0].toString();
            log.info("[monitorRecordsCacheKeyGen] key generated: {}", s);
            return s;
        };
    }
}
