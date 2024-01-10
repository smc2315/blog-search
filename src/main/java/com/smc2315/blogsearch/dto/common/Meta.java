package com.smc2315.blogsearch.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Meta(@JsonProperty("is_end") Boolean isEnd,
                   @JsonProperty("pageable_count") int pageableCount,
                   @JsonProperty("total_count") int totalCount) {

    public static Meta of(Boolean isEnd, int pageableCount, int totalCount) {
        return new Meta(isEnd, pageableCount, totalCount);
    }
}