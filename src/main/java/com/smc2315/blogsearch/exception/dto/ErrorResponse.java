package com.smc2315.blogsearch.exception.dto;

import com.smc2315.blogsearch.exception.ApplicationError;
import com.smc2315.blogsearch.exception.ApplicationException;

public record ErrorResponse(
        String code,
        String message
) {
    public static ErrorResponse from(ApplicationException exception) {
        return new ErrorResponse(exception.getCode(), exception.getMessage());
    }

    public static ErrorResponse from(Exception exception) {
        return new ErrorResponse(ApplicationError.INTERNAL_SERVER_ERROR.getCode(), exception.getMessage());
    }
}
