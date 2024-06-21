package io.study.springbootlayered.api.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.study.springbootlayered.api.member.domain.MemberProcessor;
import io.study.springbootlayered.api.member.domain.dto.MemberSignupDto;
import io.study.springbootlayered.api.member.domain.mail.MailService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberSignupService {

    private final MemberProcessor memberProcessor;
    private final MailService mailService;

    @Transactional
    public MemberSignupDto.Info signup(final MemberSignupDto.Command request) {
        /** 회원 가입 **/
        MemberSignupDto.Info info = memberProcessor.register(request);

        /** 회원가입 완료 후 이메일 전송 **/
        String registeredEmail = info.getEmail();
        mailService.sendMail(toEmailArray(registeredEmail), "회원가입 완료 안내", "회원가입이 완료되었습니다.");

        return info;
    }

    private String[] toEmailArray(String... email) {
        return email;
    }
}
