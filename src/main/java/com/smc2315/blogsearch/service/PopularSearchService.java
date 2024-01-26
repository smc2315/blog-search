package com.smc2315.blogsearch.service;

import com.smc2315.blogsearch.aop.lock.DistributeLock;
import com.smc2315.blogsearch.dto.mapper.PopularSearchMapper;
import com.smc2315.blogsearch.dto.response.PopularSearchResponse;
import com.smc2315.blogsearch.entity.PopularSearch;
import com.smc2315.blogsearch.repository.PopularSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class PopularSearchService {

    private final PopularSearchRepository popularSearchRepository;

    @DistributeLock(key = "#keyword")
    public void increaseSearchCount(String keyword) {
        popularSearchRepository.findByKeyword(keyword)
                .ifPresentOrElse(
                        PopularSearch::increaseCount,
                        () -> popularSearchRepository.save(PopularSearch.create(keyword))
                );
    }

    @Transactional(readOnly = true)
    public List<PopularSearchResponse> findTop10Keywords() {
        return popularSearchRepository.findTop10Keywords().stream()
                .map(PopularSearchMapper.INSTANCE::toPopularSearchResponse)
                .collect(Collectors.toList());
    }
}
