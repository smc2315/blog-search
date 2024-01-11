package com.smc2315.blogsearch.external.client;

import com.smc2315.blogsearch.config.KakaoOpenFeignConfiguration;
import com.smc2315.blogsearch.external.dto.KakaoApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "KakaoBlogSearchOpenFeign", qualifiers = "kakao", url = "${blog.search.kakao.api-uri}", configuration = KakaoOpenFeignConfiguration.class)
public interface KakaoBlogSearchOpenFeign extends BlogSearchOpenFeign {

    @GetMapping
    @Override
    KakaoApiResponse searchBlogs(
            @RequestParam String query,
            @RequestParam Integer page,
            @RequestParam String sort
    );
}
