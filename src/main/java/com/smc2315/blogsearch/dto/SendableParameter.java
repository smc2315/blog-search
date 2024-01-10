package com.smc2315.blogsearch.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Getter
public class SendableParameter {

    private final String keyword;

    public static SendableParameter of(String keyword) {
        return new SendableParameter(keyword);
    }
}
