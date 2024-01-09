package com.smc2315.blogsearch.dto.request;

public record BlogSearchRequest(
        String query,
        Integer page,
        String sort
) {

}
