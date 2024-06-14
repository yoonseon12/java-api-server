package io.study.springbootlayered.web.exception.error;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    HttpStatus getHttpStatus();

    String getDescription();

}
