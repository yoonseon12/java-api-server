package io.study.springbootlayered.api.member.domain;

import org.springframework.stereotype.Component;

import io.study.springbootlayered.api.member.domain.dto.MemberSignupInternalDto;
import io.study.springbootlayered.api.member.domain.entity.Member;
import io.study.springbootlayered.api.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberProcessorImpl implements MemberProcessor {

    private final MemberRepository memberRepository;

    @Override
    public MemberSignupInternalDto.Response register(final MemberSignupInternalDto.Request request) {
        Member initBasicMember = Member.createBasicMember(request.getEmail(), request.getNickname(), request.getPassword());
        Member savedMember = memberRepository.save(initBasicMember);

        return new MemberSignupInternalDto.Response(savedMember.getEmail());
    }

}
