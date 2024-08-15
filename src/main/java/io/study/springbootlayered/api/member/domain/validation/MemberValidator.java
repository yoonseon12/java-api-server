package io.study.springbootlayered.api.member.domain.validation;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.study.springbootlayered.api.member.domain.dto.MemberSignupDto;
import io.study.springbootlayered.api.member.domain.repository.MemberQueryRepository;
import io.study.springbootlayered.web.exception.ApiException;
import io.study.springbootlayered.web.exception.error.MemberErrorCode;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberValidator {

    private final MemberQueryRepository memberQueryRepository;

    public void signinValidate(final MemberSignupDto.Command request) {
        validateDuplicateNickname(request.getNickname());
        validateDuplicateEmail(request.getEmail());
    }

    private void validateDuplicateNickname(final String nickname) {
        if (memberQueryRepository.existsByNickname(nickname)) {
            throw new ApiException(MemberErrorCode.CONFLICT_DUPLICATE_NICKNAME);
        }
    }

    private void validateDuplicateEmail(final String email) {
        if (memberQueryRepository.existsByEmail(email)) {
            throw new ApiException(MemberErrorCode.CONFLICT_DUPLICATE_EMAIL);
        }
    }
}
