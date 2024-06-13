package io.study.springbootlayered.api.member.application;

import org.springframework.stereotype.Service;

import io.study.springbootlayered.api.member.domain.MemberProcessor;
import io.study.springbootlayered.api.member.domain.dto.MemberSignupInternalDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberSignupService {

    private final MemberProcessor memberProcessor;

    @Transactional
    public MemberSignupInternalDto.Response signup(final MemberSignupInternalDto.Request request) {
        return memberProcessor.register(request);
    }
}
