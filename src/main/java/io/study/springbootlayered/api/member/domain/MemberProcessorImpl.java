package io.study.springbootlayered.api.member.domain;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import io.study.springbootlayered.api.member.domain.dto.MemberSignupDto;
import io.study.springbootlayered.api.member.domain.entity.Member;
import io.study.springbootlayered.api.member.domain.repository.MemberRepository;
import io.study.springbootlayered.api.member.domain.validation.MemberValidator;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberProcessorImpl implements MemberProcessor {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberValidator memberValidator;

    @Override
    public MemberSignupDto.Info register(final MemberSignupDto.Command request) {
        memberValidator.validate(request);
        Member initBasicMember = Member.createBasicMember(request.getEmail(), request.getNickname(),
            encodePassword(request.getPassword()));
        Member savedMember = memberRepository.save(initBasicMember);

        return new MemberSignupDto.Info(savedMember.getEmail());
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
