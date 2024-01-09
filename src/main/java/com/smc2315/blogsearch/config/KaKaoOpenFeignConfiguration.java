package com.smc2315.blogsearch.config;

import com.smc2315.blogsearch.external.interceptor.KakaoOpenFeignAuthorizationInterceptor;
import org.springframework.context.annotation.Bean;

public class KaKaoOpenFeignConfiguration {

    @Bean
    public KakaoOpenFeignAuthorizationInterceptor feignInterceptor() {
        return new KakaoOpenFeignAuthorizationInterceptor();
    }
}
