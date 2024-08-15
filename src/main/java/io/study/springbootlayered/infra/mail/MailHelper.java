package io.study.springbootlayered.infra.mail;

public interface MailHelper {

    void sendMail(String[] toEmailArray, String title, String content);

}
