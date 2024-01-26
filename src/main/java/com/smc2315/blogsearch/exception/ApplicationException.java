package com.smc2315.blogsearch.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException {

    private final HttpStatus status;
    private final String code;

    public ApplicationException(ApplicationError applicationError) {
        super(applicationError.getMessage());
        status = applicationError.getStatus();
        code = applicationError.getCode();
    }
}
