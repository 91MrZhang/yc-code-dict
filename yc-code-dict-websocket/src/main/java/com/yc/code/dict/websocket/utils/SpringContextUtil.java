package com.yc.code.dict.websocket.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * SpringContextUtil
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }
    

    public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}


	public static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }
   
}
