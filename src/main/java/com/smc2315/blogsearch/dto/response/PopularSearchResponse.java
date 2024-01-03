package com.smc2315.blogsearch.dto.response;

public record PopularSearchResponse(
        String keyword,
        int count
) {

}
