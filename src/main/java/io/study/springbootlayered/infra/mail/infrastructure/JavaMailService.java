package io.study.springbootlayered.infra.mail.infrastructure;

import java.io.UnsupportedEncodingException;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import io.study.springbootlayered.infra.mail.MailService;
import io.study.springbootlayered.web.exception.AsyncException;
import io.study.springbootlayered.web.exception.error.MailErrorCode;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JavaMailService implements MailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendMail(String[] toEmailArray, String title, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

            messageHelper.setTo(toEmailArray);
            messageHelper.setSubject(title);
            messageHelper.setFrom(new InternetAddress("noreply.yoonseon3@gmail.com", "이윤선", "UTF-8"));
            messageHelper.setText(content, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new AsyncException(MailErrorCode.MAIL_SEND_ERROR);
        }

        mailSender.send(message);
    }
}
