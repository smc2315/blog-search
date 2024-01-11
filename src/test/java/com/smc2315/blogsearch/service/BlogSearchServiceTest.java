package com.smc2315.blogsearch.service;

import com.smc2315.blogsearch.dto.common.Document;
import com.smc2315.blogsearch.dto.common.Meta;
import com.smc2315.blogsearch.dto.request.BlogSearchRequest;
import com.smc2315.blogsearch.dto.response.BlogSearchResponse;
import com.smc2315.blogsearch.external.dto.KakaoApiResponse;
import com.smc2315.blogsearch.external.BlogSearchApiCaller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class BlogSearchServiceTest {

    @Mock
    private BlogSearchApiCaller blogSearchApiCaller;

    @InjectMocks
    private BlogSearchService blogSearchService;

    @Test
    void 블로그_검색_결과를_반환한다() {
        //given
        final String query = "용태";
        final int page = 1;
        final String sort = "recency";
        final BlogSearchRequest blogSearchRequest = new BlogSearchRequest(query, page, sort);
        final String blogName = "최강용태의 OAuth2 강의";
        final String contents = "소셜 로그인은 이렇게 하는거다";
        final OffsetDateTime dateTime = OffsetDateTime.now();
        final String thumbnail = "null";
        final String title = "소셜 로그인 작성 가이드";
        final String url = "https://test.com";
        final boolean isEnd = true;
        final int pageableCount = 10;
        int totalCount = 100;
        Document document = new Document(blogName, contents, dateTime, thumbnail, title, url);
        Meta meta = new Meta(isEnd, pageableCount, totalCount);
        KakaoApiResponse kakaoApiResponse = new KakaoApiResponse(List.of(document), meta);
        given(blogSearchApiCaller.searchBlogs(blogSearchRequest)).willReturn(kakaoApiResponse);

        //when
        BlogSearchResponse blogSearchResponse = blogSearchService.getBlogSearchResults(blogSearchRequest);

        //then
        assertThat(blogSearchResponse.documents().get(0).blogName()).isEqualTo(blogName);
        assertThat(blogSearchResponse.documents().get(0).contents()).isEqualTo(contents);
        assertThat(blogSearchResponse.documents().get(0).thumbnail()).isEqualTo(thumbnail);
        assertThat(blogSearchResponse.documents().get(0).title()).isEqualTo(title);
        assertThat(blogSearchResponse.documents().get(0).url()).isEqualTo(url);
    }
}
