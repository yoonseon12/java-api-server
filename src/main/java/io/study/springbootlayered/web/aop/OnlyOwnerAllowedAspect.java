package io.study.springbootlayered.web.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import io.study.springbootlayered.api.member.domain.entity.Member;
import io.study.springbootlayered.api.member.domain.repository.MemberQueryRepository;
import io.study.springbootlayered.web.exception.ApiException;
import io.study.springbootlayered.web.exception.error.AuthErrorCode;
import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class OnlyOwnerAllowedAspect {

    private final MemberQueryRepository memberQueryRepository;

    @Before("@annotation(io.study.springbootlayered.web.annotation.OnlyOwnerAllowed) && args(memberId)")
    public void beforeMethodWithOnlyOwnerAllowed(final Long memberId) {

        String loginEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        Member findMember = memberQueryRepository.findById(memberId)
            .orElseThrow(() -> new ApiException(AuthErrorCode.FORBIDDEN_REQUEST));

        validateSelfEmail(findMember.getEmail(), loginEmail);
    }

    private void validateSelfEmail(final String targetEmail, final String loginEmail) {
        if (!targetEmail.equals(loginEmail)) {
            throw new ApiException(AuthErrorCode.FORBIDDEN_REQUEST);
        }
    }
}
