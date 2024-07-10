package io.study.springbootlayered.api.member.domain;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import io.study.springbootlayered.api.member.domain.dto.MemberDetailDto;
import io.study.springbootlayered.api.member.domain.dto.MemberPasswordResetDto;
import io.study.springbootlayered.api.member.domain.dto.MemberSignupDto;
import io.study.springbootlayered.api.member.domain.entity.Member;
import io.study.springbootlayered.api.member.domain.validation.MemberValidator;
import io.study.springbootlayered.api.member.infrastructure.MemberQueryRepository;
import io.study.springbootlayered.api.member.infrastructure.MemberRepository;
import io.study.springbootlayered.web.exception.ApiException;
import io.study.springbootlayered.web.exception.error.MemberErrorCode;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberProcessorImpl implements MemberProcessor {

    private final MemberQueryRepository memberQueryRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberValidator memberValidator;

    @Override
    public MemberSignupDto.Info register(final MemberSignupDto.Command request) {
        memberValidator.signinValidate(request);
        Member initBasicMember = Member.createBasicMember(request.getEmail(), request.getNickname(),
            encodePassword(request.getPassword()));
        Member savedMember = memberRepository.save(initBasicMember);

        return new MemberSignupDto.Info(savedMember.getEmail());
    }

    @Override
    public MemberDetailDto.Info getMember(final Long memberId) {
        Member findMember = findById(memberId);

        return MemberDetailDto.Info.of(findMember);
    }

    @Override
    public void resetPassword(final MemberPasswordResetDto.Command command) {
        Member findMember = findByEmail(command.getEmail());
        findMember.changePassword(encodePassword(command.getPassword()));
    }

    private Member findById(final Long memberId) {
        return memberQueryRepository.findById(memberId)
            .orElseThrow(() -> new ApiException(MemberErrorCode.NOT_FOUND_MEMBER));
    }

    private Member findByEmail(final String email) {
        return memberQueryRepository.findByEmail(email)
            .orElseThrow(() -> new ApiException(MemberErrorCode.NOT_FOUND_MEMBER));
    }

    private String encodePassword(final String password) {
        return passwordEncoder.encode(password);
    }

}
