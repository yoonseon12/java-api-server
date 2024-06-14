package io.study.springbootlayered.api.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.study.springbootlayered.api.member.domain.MemberProcessor;
import io.study.springbootlayered.api.member.domain.dto.MemberSignupInternalDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberSignupService {

    private final MemberProcessor memberProcessor;

    @Transactional
    public MemberSignupInternalDto.Response signup(final MemberSignupInternalDto.Request request) {
        return memberProcessor.register(request);
    }
}
