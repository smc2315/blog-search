package com.smc2315.blogsearch.external.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

public class OpenFeignAuthorizationInterceptor implements RequestInterceptor {

    private static final String KAKAO_AUTHORIZATION_KEY_PREFIX = "KakaoAK ";

    @Value("${blog.search.kakao.api-key}")
    private static String KAKAO_API_KEY;

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", KAKAO_AUTHORIZATION_KEY_PREFIX + KAKAO_API_KEY);
    }
}
