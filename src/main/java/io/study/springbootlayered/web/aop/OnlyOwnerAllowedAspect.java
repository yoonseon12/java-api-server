package io.study.springbootlayered.web.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import io.study.springbootlayered.api.member.domain.entity.Member;
import io.study.springbootlayered.api.member.domain.repository.MemberRepository;
import io.study.springbootlayered.web.exception.ApiException;
import io.study.springbootlayered.web.exception.error.LoginErrorCode;
import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class OnlyOwnerAllowedAspect {

    private final MemberRepository memberRepository;

    @Before("@annotation(io.study.springbootlayered.web.annotation.OnlyOwnerAllowed) && args(memberId)")
    public void beforeMethodWithOnlyOwnerAllowed(final Long memberId) {

        String loginEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        Member findMember = memberRepository.findById(memberId)
            .orElseThrow(() -> new ApiException(LoginErrorCode.FORBIDDEN));

        validateSelfEmail(findMember.getEmail(), loginEmail);
    }

    private void validateSelfEmail(final String findMemberEmail, final String loginEmail) {
        if (!findMemberEmail.equals(loginEmail)) {
            throw new ApiException(LoginErrorCode.FORBIDDEN);
        }
    }
}
