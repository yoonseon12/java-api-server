package io.study.springbootlayered.web.exception.error;

import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MailErrorCode implements ErrorCode {

    MAIL_SEND_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "메일 전송 중 에러가 발생했습니다.");

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
