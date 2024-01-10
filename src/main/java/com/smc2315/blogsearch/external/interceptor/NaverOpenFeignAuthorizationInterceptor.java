package com.smc2315.blogsearch.external.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

public class NaverOpenFeignAuthorizationInterceptor implements RequestInterceptor {

    private static final String NAVER_CLIENT_ID_HEADER = "X-Naver-Client-Id";
    private static final String NAVER_CLIENT_SECRET_HEADER = "X-Naver-Client-Secret";

    @Value("${blog.search.naver.client-id}")
    private String NAVER_CLIENT_ID;

    @Value("${blog.search.naver.client-secret}")
    private String NAVER_CLIENT_SECRET;

    @Override
    public void apply(RequestTemplate template) {
        template.header(NAVER_CLIENT_ID_HEADER, NAVER_CLIENT_ID);
        template.header(NAVER_CLIENT_SECRET_HEADER, NAVER_CLIENT_SECRET);
    }
}
