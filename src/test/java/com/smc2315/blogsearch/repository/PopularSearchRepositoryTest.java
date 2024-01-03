package com.smc2315.blogsearch.repository;

import com.smc2315.blogsearch.entity.PopularSearch;
import com.smc2315.blogsearch.config.TestQuerydslConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@Import({TestQuerydslConfiguration.class})
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class PopularSearchRepositoryTest {

    @Autowired
    PopularSearchRepository popularSearchRepository;

    @Test
    void 인기검색어_최대_10개를_조회한다() {
        // given
        final long count = 10;

        // when
        List<PopularSearch> keywords = popularSearchRepository.findTop10Keywords();

        // then
        assertEquals(count, keywords.size());
    }
}
