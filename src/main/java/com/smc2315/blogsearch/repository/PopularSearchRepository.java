package com.smc2315.blogsearch.repository;

import com.smc2315.blogsearch.entity.PopularSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PopularSearchRepository extends JpaRepository<PopularSearch, Long>, CustomPopularSearchRepository {

    Optional<PopularSearch> findByKeyword(String keyword);
}
