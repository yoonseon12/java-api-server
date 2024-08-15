package io.study.springbootlayered.web.config;

import org.assertj.core.api.Assertions;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

class JasyptConfigTest {

    @Test
    void customJasyptTest() {
        String password = " ";
        StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword(password);
        jasypt.setAlgorithm("PBEWithMD5AndDES");

        String targetStr = " ";
        String encrypted = jasypt.encrypt(targetStr);
        System.out.println("encrypted = " + encrypted);

        String decrypted = jasypt.decrypt(encrypted);
        System.out.println("decrypted = " + decrypted);

        Assertions.assertThat(decrypted).isEqualTo(targetStr);
    }

}