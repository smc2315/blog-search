package com.smc2315.blogsearch.service;

import com.smc2315.blogsearch.dto.mapper.PopularSearchMapper;
import com.smc2315.blogsearch.dto.response.PopularSearchResponse;
import com.smc2315.blogsearch.repository.PopularSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PopularSearchService {

    private final PopularSearchRepository popularSearchRepository;

    public List<PopularSearchResponse> findTop10Keywords() {
        return popularSearchRepository.findTop10Keywords().stream()
                .map(PopularSearchMapper.INSTANCE::toPopularSearchResponse)
                .collect(Collectors.toList());
    }
}
