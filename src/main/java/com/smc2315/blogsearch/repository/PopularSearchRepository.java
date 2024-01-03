package com.smc2315.blogsearch.repository;

import com.smc2315.blogsearch.PopularSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PopularSearchRepository extends JpaRepository<PopularSearch, Long>, CustomPopularSearchRepository {
}
