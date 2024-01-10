package com.smc2315.blogsearch.service.event;

import com.smc2315.blogsearch.aop.BlogSearchEvent;
import com.smc2315.blogsearch.dto.SendableParameter;
import com.smc2315.blogsearch.service.PopularSearchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BlogSearchEventListenerTest {

    @Mock
    private PopularSearchService popularSearchService;

    @InjectMocks
    private BlogSearchEventListener blogSearchEventListener;

    @Test
    void givenBlogSearchEvent_whenHandle_thenIncreaseSearchCount() {
        final String keyword = "keyword";
        // Given
        BlogSearchEvent blogSearchEvent = new BlogSearchEvent(SendableParameter.of(keyword));

        // When
        blogSearchEventListener.handle(blogSearchEvent);

        // Then
        ArgumentCaptor<String> keywordCaptor = ArgumentCaptor.forClass(String.class);
        verify(popularSearchService).increaseSearchCount(keywordCaptor.capture());

        assertEquals(keyword, keywordCaptor.getValue());
    }
}
