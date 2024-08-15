package io.study.springbootlayered.api.member.ui.dto;

import io.study.springbootlayered.web.annotation.ValidEmail;
import io.study.springbootlayered.web.annotation.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostMemberSigninDto {

    @Getter
    @RequiredArgsConstructor
    public static class Request {
        @ValidEmail
        private final String email;
        @ValidPassword
        private final String password;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Response {
        @NotBlank
        private final String accessToken;
        @NotBlank
        private final String refreshToken;
    }
}
