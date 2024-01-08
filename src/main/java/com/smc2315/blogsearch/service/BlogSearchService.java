package com.smc2315.blogsearch.service;

import com.smc2315.blogsearch.dto.mapper.BlogSearchMapper;
import com.smc2315.blogsearch.dto.request.BlogSearchRequest;
import com.smc2315.blogsearch.dto.response.BlogSearchResponse;
import com.smc2315.blogsearch.external.BlogSearchApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogSearchService {

    private final BlogSearchApiCaller blogSearchApiCaller;

    public BlogSearchResponse getBlogSearchResults(BlogSearchRequest blogSearchRequest) {
        return BlogSearchMapper.INSTANCE
                .toBlogSearchResponse(blogSearchApiCaller.searchBlogs(blogSearchRequest));
    }
}
