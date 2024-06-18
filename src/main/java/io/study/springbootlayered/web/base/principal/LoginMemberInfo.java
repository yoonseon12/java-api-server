package io.study.springbootlayered.web.base.principal;

import java.util.List;

import io.study.springbootlayered.api.member.domain.entity.AuthorityType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginMemberInfo {
    private final String email;
    private final List<AuthorityType> roles;
}
