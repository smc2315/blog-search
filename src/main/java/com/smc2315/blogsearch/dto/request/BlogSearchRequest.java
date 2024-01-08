package com.smc2315.blogsearch.dto.request;

public record BlogSearchRequest(
        String query,
        int page,
        String sort
) {

}
