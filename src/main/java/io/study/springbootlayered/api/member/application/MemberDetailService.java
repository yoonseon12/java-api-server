package io.study.springbootlayered.api.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.study.springbootlayered.api.member.domain.MemberProcessor;
import io.study.springbootlayered.api.member.domain.dto.MemberDetailDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberDetailService {

    private final MemberProcessor memberProcessor;

    public MemberDetailDto.Info detail(final Long memberId, final String loginEmail) {
        return memberProcessor.getMember(memberId, loginEmail);
    }
}
