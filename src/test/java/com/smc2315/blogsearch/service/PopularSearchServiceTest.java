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
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PopularSearchServiceTest {

    @Mock
    private PopularSearchRepository popularSearchRepository;

    @InjectMocks
    private PopularSearchService popularSearchService;

    @Test
    public void 인기검색어_최대_10개를_조회한다() {
        //given
        PopularSearch popularSearch = PopularSearch.builder()
                .keyword("keyword")
                .count(1)
                .build();
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
