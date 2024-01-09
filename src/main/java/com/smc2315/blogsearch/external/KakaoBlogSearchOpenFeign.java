package com.smc2315.blogsearch.external;

import com.smc2315.blogsearch.config.KaKaoOpenFeignConfiguration;
import com.smc2315.blogsearch.dto.response.KakaoApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "KakaoBlogSearchOpenFeign", url = "${blog.search.kakao.api-uri}", configuration = KaKaoOpenFeignConfiguration.class)
public interface KakaoBlogSearchOpenFeign extends BlogSearchOpenFeign{

    @GetMapping
    @Override
    KakaoApiResponse searchBlogs(
            @RequestParam String query,
            @RequestParam Integer page,
            @RequestParam String sort
    );
}
