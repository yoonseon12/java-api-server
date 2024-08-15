package io.study.springbootlayered.api.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.study.springbootlayered.api.member.domain.MemberProcessor;
import io.study.springbootlayered.api.member.domain.dto.MemberSignupDto;
import io.study.springbootlayered.api.member.domain.event.SignupEvent;
import io.study.springbootlayered.web.base.Events;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberSignupUseCase {

    private final MemberProcessor memberProcessor;

    @Transactional
    public MemberSignupDto.Info signup(final MemberSignupDto.Command request) {
        // 1. 회원 가입
        MemberSignupDto.Info info = memberProcessor.register(request);

        // 2. 회원가입 완료 후 이메일 전송
        String registeredEmail = info.getEmail();
        Events.raise(SignupEvent.of(registeredEmail));

        return info;
    }
}
