package com.smc2315.blogsearch.config;

import com.smc2315.blogsearch.external.interceptor.OpenFeignAuthorizationInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients("com.smc2315.blogsearch.external")
@Configuration
public class OpenFeignConfiguration {

    @Bean
    public OpenFeignAuthorizationInterceptor feignInterceptor() {
        return new OpenFeignAuthorizationInterceptor();
    }
}
