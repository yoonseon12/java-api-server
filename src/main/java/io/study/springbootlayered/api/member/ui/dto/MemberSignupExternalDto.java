package io.study.springbootlayered.api.member.ui.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberSignupExternalDto {

    @Getter
    @RequiredArgsConstructor
    public static class Request {
        private final String nickname;
        private final String email;
        private final String password;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Response {
        private final String email;
    }

}
