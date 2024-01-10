package com.smc2315.blogsearch.dto.mapper;

import com.smc2315.blogsearch.dto.common.Document;
import com.smc2315.blogsearch.dto.response.NaverApiResponse.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Mapper
public interface DocumentMapper {

    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "link", target = "url")
    @Mapping(source = "description", target = "contents")
    @Mapping(source = "bloggerName", target = "blogName")
    @Mapping(target = "datetime", expression = "java(mapDateTime(item.postDate()))")
    Document toDocument(Item item);

    default OffsetDateTime mapDateTime(String postDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(postDate, formatter);
        return localDate.atStartOfDay().atOffset(ZoneOffset.UTC);
    }
}
