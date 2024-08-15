package io.study.springbootlayered.api.member.domain.dto;

import java.util.List;

import io.study.springbootlayered.api.member.domain.entity.AuthorityType;
import io.study.springbootlayered.api.member.domain.entity.Member;
import io.study.springbootlayered.api.member.domain.entity.MemberAuthority;
import io.study.springbootlayered.web.annotation.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberDetailDto {

    @Getter
    @RequiredArgsConstructor
    public static class Info {
        @ValidEmail
        private final String email;
        @NotBlank
        private final String nickname;
        @NotNull
        private final List<AuthorityType> roles;

        public static MemberDetailDto.Info of(Member member) {
            List<AuthorityType> roles = member.getAuthorities().stream()
                .map(MemberAuthority::getAuthority)
                .toList();
            return new MemberDetailDto.Info(member.getEmail(), member.getNickname(), roles);
        }
    }
}
