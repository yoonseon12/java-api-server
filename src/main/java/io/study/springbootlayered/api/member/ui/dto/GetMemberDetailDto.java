package io.study.springbootlayered.api.member.ui.dto;

import java.util.List;

import io.study.springbootlayered.api.member.domain.entity.AuthorityType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetMemberDetailDto {

    @Getter
    @RequiredArgsConstructor
    public static class Response {
        private final String email;
        private final String nickname;
        private final List<AuthorityType> roles;
    }
}
