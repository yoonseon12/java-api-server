package io.study.springbootlayered.api.member.domain.event;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import io.study.springbootlayered.api.member.domain.mail.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberEventListener {

    private final MailService mailService;

    @Async
    @TransactionalEventListener
    public void signupEventListener(final SignupEvent event) {
        log.info("MemberEventListener.signupEventListener !!");

        String[] toEmail = toEmailArray(event.getEmail());
        mailService.sendMail(toEmail, "회원가입 완료 안내", "회원가입이 완료되었습니다.");
    }

    private String[] toEmailArray(String... email) {
        return email;
    }
}
