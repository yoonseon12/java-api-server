package io.study.springbootlayered.api.member.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.study.springbootlayered.api.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberDetails implements UserDetails {

    private final transient Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return member.getAuthorities()
            .stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().toString()))
            .toList();
    }

    @Override
    public String getPassword() {
        return member.getPassword().getValue();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    // 계정이 만료되었는지 확인 true - 만료되지 않음
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있는지 확인 true - 잠겨있지 않음.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정의 패스워드가 만료되었는지 확인 true - 만료되지 않음.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 사용가능한지 확인 true - 사용 가능.
    @Override
    public boolean isEnabled() {
        return true;
    }
}
