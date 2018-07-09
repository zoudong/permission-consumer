package com.zoudong.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * @author zd
 * @description class
 * @date 2018/6/20 17:19
 */
@Slf4j
@Configuration
public class FilterConfig {
    /**
     * 配置过滤器
     * @return
     */
    /*@Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
       registration.setFilter(new AccessTokenFilter());
        registration.addUrlPatterns("/*");
        registration.setName("accessTokenFilter");
        return registration;
    }*/

}
