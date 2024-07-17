package io.study.springbootlayered.web.exception.error;

import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {

    FORBIDDEN_REQUEST(HttpStatus.FORBIDDEN, "요청 권한이 없습니다."),

    INVALID_ACCOUNT(HttpStatus.UNAUTHORIZED, "유효하지 않은 로그인 정보입니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");

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
