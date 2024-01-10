package com.smc2315.blogsearch.service;

import com.smc2315.blogsearch.aop.BlogSearchEvent;
import com.smc2315.blogsearch.aop.PublishEvent;
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

    @PublishEvent(eventType = BlogSearchEvent.class, params = "#{T(com.smc2315.blogsearch.dto.SendableParameter).of(query)}")
    public BlogSearchResponse getBlogSearchResults(BlogSearchRequest blogSearchRequest) {
        return BlogSearchMapper.INSTANCE
                .toBlogSearchResponse(blogSearchApiCaller.searchBlogs(blogSearchRequest));
    }
}
