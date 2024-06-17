package io.study.springbootlayered.web.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private final String secret;
    private final long accessTokenExpireTime;
    private final long refreshTokenExpireTime;
}
