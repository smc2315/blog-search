package com.smc2315.blogsearch.controller;

import com.smc2315.blogsearch.dto.response.PopularSearchResponse;
import com.smc2315.blogsearch.service.PopularSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/popular")
public class PopularSearchController {

    private final PopularSearchService popularSearchService;

    @GetMapping("/search")
    public ResponseEntity<List<PopularSearchResponse>> getPopularSearchResults() {
        return ResponseEntity.ok().body(popularSearchService.findTop10Keywords());
    }
}
