package io.study.springbootlayered.web.exception;

import io.study.springbootlayered.web.exception.error.ErrorCode;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private final transient ErrorCode errorCode;

    public ApiException(final ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }
}
