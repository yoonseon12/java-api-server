package io.study.springbootlayered.web.base.response;

import io.study.springbootlayered.web.exception.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    private final int status;
    private final String message;

    public static ErrorResponse of(final ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getHttpStatus().value(), errorCode.getDescription());
    }

    public static ErrorResponse of(final int status, final String message) {
        return new ErrorResponse(status, message);
    }

}
