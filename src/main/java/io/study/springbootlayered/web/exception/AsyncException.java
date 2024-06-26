package io.study.springbootlayered.web.exception;

import io.study.springbootlayered.web.exception.error.ErrorCode;
import lombok.Getter;

@Getter
public class AsyncException extends RuntimeException {

    private final transient ErrorCode errorCode;

    public AsyncException(final ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }
}
