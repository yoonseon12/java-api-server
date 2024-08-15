package io.study.springbootlayered.api.member.ui.dto;

import java.util.List;

import io.study.springbootlayered.api.member.domain.entity.AuthorityType;
import io.study.springbootlayered.web.annotation.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetMemberDetailDto {

    @Getter
    @RequiredArgsConstructor
    public static class Response {
        @ValidEmail
        private final String email;
        @NotBlank
        private final String nickname;
        @NotNull
        private final List<AuthorityType> roles;
    }
}
