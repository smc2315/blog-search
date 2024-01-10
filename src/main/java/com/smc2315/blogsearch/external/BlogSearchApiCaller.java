package com.smc2315.blogsearch.external;

import com.smc2315.blogsearch.dto.request.BlogSearchRequest;
import com.smc2315.blogsearch.dto.response.ApiResponse;
import com.smc2315.blogsearch.external.client.BlogSearchOpenFeign;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class BlogSearchApiCaller {

    private final BlogSearchOpenFeign kakaoBlogSearchOpenFeign;
    private final BlogSearchOpenFeign naverBlogSearchOpenFeign;

    public BlogSearchApiCaller(@Qualifier("kakao") BlogSearchOpenFeign kakaoBlogSearchOpenFeign,
                               @Qualifier("naver") BlogSearchOpenFeign naverBlogSearchOpenFeign) {
        this.kakaoBlogSearchOpenFeign = kakaoBlogSearchOpenFeign;
        this.naverBlogSearchOpenFeign = naverBlogSearchOpenFeign;
    }

    @Retryable(maxAttempts = 1)
    public ApiResponse searchBlogs(BlogSearchRequest blogSearchRequest) {
        return kakaoBlogSearchOpenFeign.searchBlogs(
                blogSearchRequest.query(),
                blogSearchRequest.page(),
                blogSearchRequest.sort()
        );
    }

    @Recover
    public ApiResponse searchBlogsFailureRecover(BlogSearchRequest blogSearchRequest) {
        return naverBlogSearchOpenFeign.searchBlogs(
                blogSearchRequest.query(),
                blogSearchRequest.page(),
                blogSearchRequest.sort()
        );
    }
}
