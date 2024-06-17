package io.study.springbootlayered.api.member.domain.validation;

import org.springframework.stereotype.Component;

import io.study.springbootlayered.api.member.domain.dto.MemberSignupDto;
import io.study.springbootlayered.api.member.domain.repository.MemberRepository;
import io.study.springbootlayered.web.exception.ApiException;
import io.study.springbootlayered.web.exception.error.MemberErrorCode;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberValidator {

    private final MemberRepository memberRepository;

    public void validate(final MemberSignupDto.Command request) {
        nicknameDuplicateValidation(request.getNickname());
        emailDuplicateValidation(request.getEmail());
    }

    private void nicknameDuplicateValidation(final String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new ApiException(MemberErrorCode.CONFLICT_DUPLICATE_NICKNAME);
        }
    }

    private void emailDuplicateValidation(final String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new ApiException(MemberErrorCode.CONFLICT_DUPLICATE_EMAIL);
        }
    }
}
