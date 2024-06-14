package io.study.springbootlayered.api.member.domain.validation;

import org.springframework.stereotype.Component;

import io.study.springbootlayered.api.member.domain.dto.MemberSignupInternalDto;
import io.study.springbootlayered.api.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberValidator {

    private final MemberRepository memberRepository;

    public void validate(final MemberSignupInternalDto.Request request) {
        nicknameDuplicateValidation(request.getNickname());
        emailDuplicateValidation(request.getEmail());
    }

    private void nicknameDuplicateValidation(final String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new IllegalArgumentException("중복된 닉네임이 존재합니다.");
        }
    }

    private void emailDuplicateValidation(final String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("중복된 이메일이 존재합니다.");
        }
    }
}
