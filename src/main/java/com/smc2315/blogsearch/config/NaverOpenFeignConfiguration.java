package com.smc2315.blogsearch.config;

import com.smc2315.blogsearch.external.interceptor.NaverOpenFeignAuthorizationInterceptor;
import org.springframework.context.annotation.Bean;

public class NaverOpenFeignConfiguration {

    @Bean
    public NaverOpenFeignAuthorizationInterceptor naverFeignInterceptor() {
        return new NaverOpenFeignAuthorizationInterceptor();
    }
}
