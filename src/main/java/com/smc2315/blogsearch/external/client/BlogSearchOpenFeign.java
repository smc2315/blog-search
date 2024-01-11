package com.smc2315.blogsearch.external.client;

import com.smc2315.blogsearch.external.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;

public interface BlogSearchOpenFeign {

    @GetMapping
    ApiResponse searchBlogs(String query, Integer page, String sort);
}
