package com.smc2315.blogsearch.external;

import com.smc2315.blogsearch.dto.request.BlogSearchRequest;
import com.smc2315.blogsearch.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogSearchApiCaller {

    private final BlogSearchOpenFeign blogSearchOpenFeign;

    public ApiResponse searchBlogs(BlogSearchRequest blogSearchRequest) {
        return blogSearchOpenFeign.searchBlogs(
                blogSearchRequest.query(),
                blogSearchRequest.page(),
                blogSearchRequest.sort()
        );
    }

    public ApiResponse searchBlogsFailureRecover(BlogSearchRequest blogSearchRequest) {
        return blogSearchOpenFeign.searchBlogs(
                blogSearchRequest.query(),
                blogSearchRequest.page(),
                blogSearchRequest.sort()
        );
    }
}
