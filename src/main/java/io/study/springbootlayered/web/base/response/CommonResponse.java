package io.study.springbootlayered.web.base.response;

import lombok.Getter;

@Getter
public class CommonResponse<T> {
    private String msg;
    private T data;

    public CommonResponse(T data) {
        this.msg = "success";
        this.data = data;
    }

    public static <T> CommonResponse<T> of (T data) {
        return new CommonResponse<>(data);
    }
}
