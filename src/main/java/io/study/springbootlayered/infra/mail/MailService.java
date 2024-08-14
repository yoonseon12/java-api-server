package io.study.springbootlayered.infra.mail;

public interface MailService {

    void sendMail(String[] toEmailArray, String title, String content);

}
