package io.study.springbootlayered.api.member.ui.dto;

import io.study.springbootlayered.web.annotation.ValidEmail;
import io.study.springbootlayered.web.annotation.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostMemberSignupDto {

    @Getter
    @RequiredArgsConstructor
    public static class Request {
        @NotBlank
        private final String nickname;

        @ValidEmail
        private final String email;

        @ValidPassword
        private final String password;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Response {
        private final String email;
    }

}
