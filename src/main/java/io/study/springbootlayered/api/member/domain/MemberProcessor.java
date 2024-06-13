package io.study.springbootlayered.api.member.domain;

import io.study.springbootlayered.api.member.domain.dto.MemberSignupInternalDto;

public interface MemberProcessor {
    MemberSignupInternalDto.Response register(MemberSignupInternalDto.Request request);
}
