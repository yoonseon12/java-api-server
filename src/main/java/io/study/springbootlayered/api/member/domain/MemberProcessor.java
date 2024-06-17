package io.study.springbootlayered.api.member.domain;

import io.study.springbootlayered.api.member.domain.dto.MemberSignupDto;

public interface MemberProcessor {
    MemberSignupDto.Info register(MemberSignupDto.Command request);
}
