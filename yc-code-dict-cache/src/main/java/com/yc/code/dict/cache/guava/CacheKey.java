package com.yc.code.dict.cache.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * CacheKey
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
public class CacheKey {

    private static final long defaultInterval = 2 * 60 * 60 * 1000; //该时间要小与等于下面的expireAfterWrite

    private static final Cache<String, Long> cache = CacheBuilder.newBuilder()
            .maximumSize(4096)
            .concurrencyLevel(2)
            .expireAfterWrite(2, TimeUnit.HOURS)
            .build();

    /**
     * 判断Key是否过期，
     * 未过期则补充进缓存
     * @param key key
     * @return boolean
     */
    public static boolean isNew(String key) {
        return isNew(key, defaultInterval);
    }

    public static boolean isNew(String key, long interval) {
        Long prevTime = cache.getIfPresent(key);
        long currentTime = System.currentTimeMillis();
        boolean isNew = prevTime == null || currentTime - prevTime > interval;
        if (isNew) {
            cache.put(key, currentTime);
        }
        return isNew;
    }
}
