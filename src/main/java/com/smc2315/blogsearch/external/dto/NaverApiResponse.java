package com.smc2315.blogsearch.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smc2315.blogsearch.dto.common.Document;
import com.smc2315.blogsearch.dto.common.Meta;
import com.smc2315.blogsearch.dto.mapper.DocumentMapper;

import java.util.List;

public record NaverApiResponse(@JsonProperty("lastBuildDate") String lastBuildDate,
                               @JsonProperty("total") int total,
                               @JsonProperty("start") int start,
                               @JsonProperty("display") int display,
                               @JsonProperty("items") List<Item> items) implements ApiResponse {

    @Override
    public List<Document> getDocuments() {
        return this.items
                .stream()
                .map(DocumentMapper.INSTANCE::toDocument)
                .toList();
    }

    @Override
    public Meta getMeta() {
        return Meta.of(null, this.total, this.total);
    }

    public record Item(
            @JsonProperty("title") String title,
            @JsonProperty("link") String link,
            @JsonProperty("description") String description,
            @JsonProperty("bloggername") String bloggerName,
            @JsonProperty("bloggerlink") String bloggerLink,
            @JsonProperty("postdate") String postDate
    ) {

    }

}
