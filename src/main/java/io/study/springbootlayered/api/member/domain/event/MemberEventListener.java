package io.study.springbootlayered.api.member.domain.event;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import io.study.springbootlayered.infra.mail.MailHelper;
import io.study.springbootlayered.web.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberEventListener {

    private final MailHelper mailService;

    @Async
    @TransactionalEventListener
    public void signupEventListener(final SignupEvent event) {
        log.debug("MemberEventListener.signupEventListener !!");

        String[] toEmail = toEmailArray(event.getEmail());
        mailService.sendMail(toEmail, "회원가입 완료 안내", "회원가입이 완료되었습니다.");
    }

    private String[] toEmailArray(final String... email) {
        return email;
    }

    @Async
    @TransactionalEventListener
    public void resetPasswordEventListener(final ResetPasswordEvent event) {
        log.debug("MemberEventListener.resetPasswordEventListener !!");

        String[] toEmail = toEmailArray(event.getEmail());
        String password = event.getTempPassword();

        mailService.sendMail(toEmail, "임시 비밀번호 발급 안내", StringUtils.format("임시 비밀번호 : {}", password));
    }

}
