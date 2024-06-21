package io.study.springbootlayered.web.exception.error;

import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MailErrorCode implements ErrorCode {

    INTERNAL_MAIL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "잘못된 서명입니다.");

    private final HttpStatus httpStatus;
    private final String description;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
