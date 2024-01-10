package com.smc2315.blogsearch.config;

import com.smc2315.blogsearch.external.interceptor.KakaoOpenFeignAuthorizationInterceptor;
import org.springframework.context.annotation.Bean;

public class KakaoOpenFeignConfiguration {

    @Bean
    public KakaoOpenFeignAuthorizationInterceptor kakaoFeignInterceptor() {
        return new KakaoOpenFeignAuthorizationInterceptor();
    }
}
