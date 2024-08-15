package io.study.springbootlayered.web.base.principal;

import java.util.List;

import io.study.springbootlayered.api.member.domain.entity.AuthorityType;
import io.study.springbootlayered.web.annotation.ValidEmail;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginMemberInfo {
    @ValidEmail
    private final String email;
    @NotNull
    private final List<AuthorityType> roles;
}
