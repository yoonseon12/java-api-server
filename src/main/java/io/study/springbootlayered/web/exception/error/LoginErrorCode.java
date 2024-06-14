package io.study.springbootlayered.web.exception.error;

import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LoginErrorCode implements ErrorCode {

    INVALID_JWT_SIGNATURE(HttpStatus.BAD_REQUEST, "잘못된 서명입니다."),
    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
    UNSUPPORTED_JWT_TOKEN(HttpStatus.BAD_REQUEST, "지원하지 않는 토큰입니다."),
    INVALID_JWT_TOKEN(HttpStatus.BAD_REQUEST, "잘못된 토큰입니다."),
    JWT_UNKNOWN_ERROR(HttpStatus.BAD_REQUEST, "토큰 처리 중 에러가 발생했습니다."),

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "유효하지 않은 자격증명 입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없습니다."),

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
