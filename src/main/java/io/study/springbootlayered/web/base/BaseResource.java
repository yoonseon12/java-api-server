package io.study.springbootlayered.web.base;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public class BaseResource {
    protected final String X_API_VERSION = "x-api-version=v1";
}
