package com.smc2315.blogsearch.service;

import com.smc2315.blogsearch.aop.event.BlogSearchEvent;
import com.smc2315.blogsearch.aop.event.PublishEvent;
import com.smc2315.blogsearch.dto.mapper.BlogSearchMapper;
import com.smc2315.blogsearch.dto.request.BlogSearchRequest;
import com.smc2315.blogsearch.dto.response.BlogSearchResponse;
import com.smc2315.blogsearch.exception.ApplicationError;
import com.smc2315.blogsearch.exception.BlogSearchException;
import com.smc2315.blogsearch.external.BlogSearchApiCaller;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BlogSearchService {

    private final BlogSearchApiCaller blogSearchApiCaller;

    @PublishEvent(eventType = BlogSearchEvent.class, params = "#{T(com.smc2315.blogsearch.dto.SendableParameter).of(query)}")
    public BlogSearchResponse getBlogSearchResults(BlogSearchRequest blogSearchRequest) {
        try {
            return BlogSearchMapper.INSTANCE
                    .toBlogSearchResponse(blogSearchApiCaller.searchBlogs(blogSearchRequest));
        } catch (FeignException exception) {
            log.error(exception.getMessage(), exception);
            throw new BlogSearchException(ApplicationError.BLOG_SEARCH_ERROR);
        }
    }
}
