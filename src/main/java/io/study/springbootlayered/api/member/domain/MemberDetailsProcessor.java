package io.study.springbootlayered.api.member.domain;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.study.springbootlayered.api.member.domain.entity.Member;
import io.study.springbootlayered.api.member.infrastructure.MemberQueryJpaRepository;
import io.study.springbootlayered.web.exception.ApiException;
import io.study.springbootlayered.web.exception.error.LoginErrorCode;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberDetailsProcessor implements UserDetailsService {

    private final MemberQueryJpaRepository memberQueryRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Member member = memberQueryRepository.findByEmail(username)
            .orElseThrow(() -> new ApiException(LoginErrorCode.INVALID_ACCOUNT));

        return new MemberDetails(member);
    }
}
