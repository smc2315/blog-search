package com.smc2315.blogsearch.dto.response;

import com.smc2315.blogsearch.dto.common.Document;
import com.smc2315.blogsearch.dto.common.Meta;

import java.util.List;

public record KakaoApiResponse(List<Document> documents, Meta meta) implements ApiResponse {

    @Override
    public List<Document> getDocuments() {
        return documents;
    }

    @Override
    public Meta getMeta() {
        return meta;
    }
}
