package com.smc2315.blogsearch.controller;

import com.smc2315.blogsearch.AbstractRestDocsTest;
import com.smc2315.blogsearch.dto.common.Document;
import com.smc2315.blogsearch.dto.common.Meta;
import com.smc2315.blogsearch.dto.request.BlogSearchRequest;
import com.smc2315.blogsearch.dto.response.BlogSearchResponse;
import com.smc2315.blogsearch.service.BlogSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BlogSearchController.class)
public class BlogSearchControllerTest extends AbstractRestDocsTest {

    private static final String URI = "/api/v1/blog/search";

    @MockBean
    private BlogSearchService blogSearchService;

    @Test
    void 블로그를_검색한다() throws Exception {
        //given
        final String query = "용태";
        final int page = 1;
        final String sort = "recency";
        final String blogName = "최강용태의 OAuth2 강의";
        final String contents = "소셜 로그인은 이렇게 하는거다";
        final LocalDateTime dateTime = LocalDateTime.now();
        final String thumbnail = "null";
        final String title = "소셜 로그인 작성 가이드";
        final String url = "https://test.com";
        final boolean isEnd = true;
        final int pageableCount = 10;
        int totalCount = 100;
        Document document = new Document(blogName, contents, dateTime, thumbnail, title, url);
        Meta meta = new Meta(isEnd, pageableCount, totalCount);
        BlogSearchResponse blogSearchResponse = new BlogSearchResponse(List.of(document), meta);
        BlogSearchRequest blogSearchRequest = new BlogSearchRequest(query, page, sort);

        MultiValueMap<String, String> parameterMap = new LinkedMultiValueMap<>();
        parameterMap.add("query", query);
        parameterMap.add("page", String.valueOf(page));
        parameterMap.add("sort", "recency");

        given(blogSearchService.getBlogSearchResults(blogSearchRequest)).willReturn(blogSearchResponse);

        //when & then
        mockMvc.perform(get(URI)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
