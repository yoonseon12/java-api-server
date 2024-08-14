package io.study.springbootlayered.api.member.domain.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberPasswordResetDto {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Command {
        private final String email;
        private final String password;
    }
}
