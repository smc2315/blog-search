package com.smc2315.blogsearch.dto.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record Document(@JsonProperty("blogname") String blogName,
                       String contents,
                       @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "Asia/Seoul")
                       OffsetDateTime datetime,
                       String thumbnail,
                       String title,
                       String url) {

}