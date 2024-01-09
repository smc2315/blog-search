package com.smc2315.blogsearch.controller;

import com.smc2315.blogsearch.dto.request.BlogSearchRequest;
import com.smc2315.blogsearch.dto.response.BlogSearchResponse;
import com.smc2315.blogsearch.service.BlogSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/blog")
public class BlogSearchController {

    private final BlogSearchService blogSearchService;

    @GetMapping("/search")
    public ResponseEntity<BlogSearchResponse> searchBlogs(BlogSearchRequest blogSearchRequest) {
        return ResponseEntity.ok().body(blogSearchService.getBlogSearchResults(blogSearchRequest));
    }
}
