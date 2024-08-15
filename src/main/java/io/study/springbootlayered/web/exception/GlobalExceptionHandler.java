package io.study.springbootlayered.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.study.springbootlayered.web.base.response.ErrorResponse;
import io.study.springbootlayered.web.exception.error.AuthErrorCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(final ApiException e) {
        log.error(e.getMessage(), e);

        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
            .body(ErrorResponse.of(e.getErrorCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValidException(
        final MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);

        return ResponseEntity.status(e.getStatusCode())
            .body(
                ErrorResponse.of(e.getStatusCode().value(), e.getBindingResult().getFieldError().getDefaultMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(final AuthenticationException e) {
        log.error(e.getMessage(), e);
        AuthErrorCode errorCode = AuthErrorCode.INVALID_ACCOUNT;
        if (e instanceof BadCredentialsException) {
            errorCode = AuthErrorCode.INVALID_PASSWORD;
        }

        return ResponseEntity.status(errorCode.getHttpStatus().value())
            .body(ErrorResponse.of(errorCode));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerException(final Exception e) {
        log.error(e.getMessage(), e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .body(ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }
}
