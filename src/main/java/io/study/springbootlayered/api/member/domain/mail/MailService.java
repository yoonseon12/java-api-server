package io.study.springbootlayered.api.member.domain.mail;

public interface MailService {

    void sendMail(String[] toEmailArray, String title, String content);

}
