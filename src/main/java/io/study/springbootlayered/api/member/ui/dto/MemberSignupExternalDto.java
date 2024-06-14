package io.study.springbootlayered.api.member.ui.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberSignupExternalDto {

    @Getter
    @RequiredArgsConstructor
    public static class Request {
        @NotBlank
        private final String nickname;

        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "이메일 형식이 잘못되었습니다.")
        @NotBlank
        @Email
        private final String email;

        @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()\\-_=+]{8,16}$", message = "비밀번호 형식이 잘못되었습니다.")
        @NotBlank
        private final String password;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Response {
        private final String email;
    }

}
