package io.study.springbootlayered.web.exception.error;

import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {

    CONFLICT_DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "중복된 닉네임이 존재합니다."),
    CONFLICT_DUPLICATE_EMAIL(HttpStatus.CONFLICT, "중복된 이메일이 존재합니다.");

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getDescription() {
        return message;
    }
}
