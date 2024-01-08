package com.smc2315.blogsearch.dto.mapper;

import com.smc2315.blogsearch.dto.response.PopularSearchResponse;
import com.smc2315.blogsearch.entity.PopularSearch;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PopularSearchMapper {

    PopularSearchMapper INSTANCE = Mappers.getMapper(PopularSearchMapper.class);

    PopularSearchResponse toPopularSearchResponse(PopularSearch popularSearch);
}
