package com.yc.code.dict.cache.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * LaunchService
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
@Slf4j
@Service
public class LaunchService {


    @Cacheable(value = "getMonitorRecords", cacheManager = "monitorRecordsCacheMgr", keyGenerator = "monitorRecordsCacheKeyGen")
    public Integer getMonitorRecords(String key) {
        log.info("未通过缓存直接访问：{}", key);
        return 1;
    }



}
