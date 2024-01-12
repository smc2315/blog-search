package com.smc2315.blogsearch.service.event;

import com.smc2315.blogsearch.aop.event.BlogSearchEvent;
import com.smc2315.blogsearch.dto.SendableParameter;
import com.smc2315.blogsearch.service.PopularSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BlogSearchEventListener {

    private final PopularSearchService popularSearchService;

    @Async
    @EventListener(classes = BlogSearchEvent.class)
    public void handle(BlogSearchEvent event) {
        SendableParameter params = event.getValue();
        popularSearchService.increaseSearchCount(params.keyword());
    }
}
