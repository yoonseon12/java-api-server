package io.study.springbootlayered.web.base.response;

import lombok.Getter;

@Getter
public class BaseResponse {
    private String message;

    public BaseResponse(String message) {
        this.message = message;
    }

    public BaseResponse() {
        this.message = "success";
    }
}
