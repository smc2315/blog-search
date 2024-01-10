package com.smc2315.blogsearch.external.client;

import com.smc2315.blogsearch.config.NaverOpenFeignConfiguration;
import com.smc2315.blogsearch.dto.response.NaverApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "NaverBlogSearchOpenFeign", qualifiers = "naver", url = "${blog.search.naver.api-uri}", configuration = NaverOpenFeignConfiguration.class)
public interface NaverBlogSearchOpenFeign extends BlogSearchOpenFeign {

    @GetMapping
    @Override
    NaverApiResponse searchBlogs(
            @RequestParam String query,
            @RequestParam Integer start,
            @RequestParam String sort
    );
}
