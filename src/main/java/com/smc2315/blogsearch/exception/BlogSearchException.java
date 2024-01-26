package com.smc2315.blogsearch.exception;

public class BlogSearchException extends ApplicationException {

    public BlogSearchException(ApplicationError applicationError) {
        super(applicationError);
    }
}
