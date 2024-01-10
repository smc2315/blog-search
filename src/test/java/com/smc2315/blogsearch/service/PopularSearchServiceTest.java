package com.smc2315.blogsearch.service;

import com.smc2315.blogsearch.dto.response.PopularSearchResponse;
import com.smc2315.blogsearch.entity.PopularSearch;
import com.smc2315.blogsearch.repository.PopularSearchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PopularSearchServiceTest {

    @Mock
    private PopularSearchRepository popularSearchRepository;

    @InjectMocks
    private PopularSearchService popularSearchService;

    @Test
    void 존재하는_검색어를_저장한다() {
        //given
        final String keyword = "keyword";
        PopularSearch popularSearch = PopularSearch.create(keyword);
        given(popularSearchRepository.findByKeyword(keyword)).willReturn(Optional.of(popularSearch));
        //when
        popularSearchService.increaseSearchCount(keyword);
        //then
        assertAll(
                () -> verify(popularSearchRepository).findByKeyword(anyString()),
                () -> verify(popularSearchRepository, never()).save(any(PopularSearch.class))
        );

    }

    @Test
    void 존재하지않는_검색어를_저장한다() {
        //given
        final String keyword = "keyword";
        given(popularSearchRepository.findByKeyword(keyword)).willReturn(Optional.empty());
        //when
        popularSearchService.increaseSearchCount(keyword);
        //then
        assertAll(
                () -> verify(popularSearchRepository).findByKeyword(anyString()),
                () -> verify(popularSearchRepository, times(1)).save(any(PopularSearch.class))
        );

    }

    @Test
    void 인기검색어_최대_10개를_조회한다() {
        //given
        final String keyword = "keyword";
        PopularSearch popularSearch = PopularSearch.create(keyword);
        List<PopularSearch> popularSearches = IntStream.rangeClosed(0, 9)
                .mapToObj(n -> popularSearch)
                .toList();
        given(popularSearchRepository.findTop10Keywords()).willReturn(popularSearches);

        //when
        List<PopularSearchResponse> popularSearchResponses = popularSearchService.findTop10Keywords();

        //then
        assertAll(
                () -> assertThat(popularSearchResponses).hasSize(10),
                () -> verify(popularSearchRepository).findTop10Keywords()
        );
    }
}
