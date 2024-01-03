package com.smc2315.blogsearch.repository;

import com.smc2315.blogsearch.PopularSearch;

import java.util.List;

public interface CustomPopularSearchRepository {
    List<PopularSearch> findTop10Keywords();
}
