package com.smc2315.blogsearch.dto;

public record SendableParameter(String keyword) {
    public static SendableParameter of(String keyword) {
        return new SendableParameter(keyword);
    }
}
