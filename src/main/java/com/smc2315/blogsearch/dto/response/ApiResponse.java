package com.smc2315.blogsearch.dto.response;

import com.smc2315.blogsearch.dto.common.Document;
import com.smc2315.blogsearch.dto.common.Meta;

import java.util.List;

public interface ApiResponse {

    List<Document> getDocuments();

    Meta getMeta();
}
