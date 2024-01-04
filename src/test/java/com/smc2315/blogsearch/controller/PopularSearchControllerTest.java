package com.smc2315.blogsearch.controller;

import com.smc2315.blogsearch.AbstractRestDocsTest;
import com.smc2315.blogsearch.dto.response.PopularSearchResponse;
import com.smc2315.blogsearch.service.PopularSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PopularSearchController.class)
public class PopularSearchControllerTest extends AbstractRestDocsTest {
    private static final String URI = "/api/v1/popular/search";

    @MockBean
    private PopularSearchService popularSearchService;

    @Test
    public void 인기_검색어를_조회한다() throws Exception {
        //given
        given(popularSearchService.findTop10Keywords()).willReturn(getPopularSearchResponseFixture());
        //when & then
        mockMvc.perform(get(URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private List<PopularSearchResponse> getPopularSearchResponseFixture() {
        return List.of(
                new PopularSearchResponse("마라탕", 1),
                new PopularSearchResponse("애플", 2),
                new PopularSearchResponse("마라탕", 3),
                new PopularSearchResponse("애플", 4),
                new PopularSearchResponse("카트라이더", 5)
        );
    }
}
