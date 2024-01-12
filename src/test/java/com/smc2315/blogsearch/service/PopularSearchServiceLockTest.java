package com.smc2315.blogsearch.service;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class PopularSearchServiceLockTest {

    @Autowired
    private PopularSearchService popularSearchService;

    @Test
    void 동시에_100명이_검색어_검색() throws InterruptedException {
        //given
        final int people = 100;
        final String keyword = "keyword";
        final CountDownLatch countDownLatch = new CountDownLatch(people);

        List<Thread> workers = Stream.generate(() -> new Thread(new SearchWorker(keyword, countDownLatch)))
                .limit(people)
                .toList();

        //when
        workers.forEach(Thread::start);
        countDownLatch.await();

        //then
        final int currentCount = popularSearchService.findTop10Keywords().get(0).count();
        System.out.println(popularSearchService.findTop10Keywords().size());
        assertEquals(people, currentCount);
    }

    @AllArgsConstructor
    private class SearchWorker implements Runnable {

        private String keyword;
        private CountDownLatch countDownLatch;

        @Override
        public void run() {
            popularSearchService.increaseSearchCount(keyword);
            countDownLatch.countDown();
        }
    }
}
