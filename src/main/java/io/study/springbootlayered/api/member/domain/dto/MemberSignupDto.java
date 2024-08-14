package io.study.springbootlayered.api.member.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberSignupDto {

    @Getter
    @RequiredArgsConstructor
    public static class Command {
        private final String email;
        private final String nickname;
        private final String password;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Info {
        private final String email;
    }
}
