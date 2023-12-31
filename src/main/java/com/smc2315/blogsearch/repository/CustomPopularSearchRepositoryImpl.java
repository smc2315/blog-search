package com.smc2315.blogsearch.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smc2315.blogsearch.entity.PopularSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.smc2315.blogsearch.entity.QPopularSearch.popularSearch;

@RequiredArgsConstructor
@Repository
public class CustomPopularSearchRepositoryImpl implements CustomPopularSearchRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PopularSearch> findTop10Keywords() {
        return jpaQueryFactory.selectFrom(popularSearch)
                .orderBy(popularSearch.count.desc(), popularSearch.keyword.asc())
                .limit(10)
                .fetch();
    }
}
