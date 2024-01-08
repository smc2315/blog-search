package com.smc2315.blogsearch.external;

import com.smc2315.blogsearch.config.OpenFeignConfiguration;
import com.smc2315.blogsearch.dto.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "KakaoBlogSearchOpenFeign", url = "${blog.search.kakao.api-uri}", configuration = OpenFeignConfiguration.class)
public interface KakaoBlogSearchOpenFeign extends BlogSearchOpenFeign{

    @GetMapping
    @Override
    ApiResponse searchBlogs(
            @RequestParam String query,
            @RequestParam int page,
            @RequestParam String sort
    );
}
