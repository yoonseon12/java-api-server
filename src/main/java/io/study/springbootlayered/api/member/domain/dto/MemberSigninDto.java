package io.study.springbootlayered.api.member.domain.dto;

import io.study.springbootlayered.web.annotation.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberSigninDto {

    @Getter
    @RequiredArgsConstructor
    public static class Command {
        @ValidEmail
        private final String email;
        @NotBlank
        private final String password;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Info {
        @NotBlank
        private final String accessToken;
        @NotBlank
        private final String refreshToken;
    }
}
