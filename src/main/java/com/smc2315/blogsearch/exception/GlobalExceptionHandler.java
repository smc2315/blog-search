package com.smc2315.blogsearch.exception;

import com.smc2315.blogsearch.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    protected ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException exception) {
        log.info("{}: {}", exception.getClass().getSimpleName(), exception.getMessage(), exception);
        return ResponseEntity
                .status(exception.getStatus())
                .body(ErrorResponse.from(exception));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(RuntimeException exception) {
        log.info("{}: {}", exception.getClass().getSimpleName(), exception.getMessage(), exception);
        return ResponseEntity
                .internalServerError()
                .body(ErrorResponse.from(exception));
    }
}
