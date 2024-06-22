package io.study.springbootlayered.web.base.response;

import lombok.Getter;

@Getter
public class CommonResponse<T> extends BaseResponse {
    private T data;

    public CommonResponse(T data) {
        super();
        this.data = data;
    }

    public static <T> CommonResponse<T> of(T data) {
        return new CommonResponse<>(data);
    }
}
