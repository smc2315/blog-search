package com.smc2315.blogsearch.dto.mapper;

import com.smc2315.blogsearch.external.dto.ApiResponse;
import com.smc2315.blogsearch.dto.response.BlogSearchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlogSearchMapper {

    BlogSearchMapper INSTANCE = Mappers.getMapper(BlogSearchMapper.class);

    BlogSearchResponse toBlogSearchResponse(ApiResponse apiResponse);
}
