package io.study.springbootlayered.api.member.application;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.study.springbootlayered.api.member.domain.MemberProcessor;
import io.study.springbootlayered.api.member.domain.dto.MemberPasswordResetDto;
import io.study.springbootlayered.api.member.domain.event.ResetPasswordEvent;
import io.study.springbootlayered.web.base.Events;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberResetUseCase {

    private final MemberProcessor memberProcessor;

    private static final String VALID_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()\\-_=+";
    private static final int MAX_SPECIAL_CHARACTER = 3;
    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 16;
    private final Random random = new SecureRandom();

    @Transactional
    public void resetPassword(final MemberPasswordResetDto.Command request) {
        String resetPassword = createResetPassword();

        memberProcessor.resetPassword(new MemberPasswordResetDto.Command(request.getEmail(), resetPassword));

        Events.raise(ResetPasswordEvent.of(request.getEmail(), resetPassword));
    }

    public String createResetPassword() {
        final int passwordLength = MIN_LENGTH + random.nextInt(MAX_LENGTH - MIN_LENGTH + 1);
        StringBuilder password = new StringBuilder(passwordLength);

        int specialCharacterLength = addSpecialCharacters(password);
        addValidCharacters(specialCharacterLength, passwordLength, password);

        return shufflePassword(password);
    }

    private String shufflePassword(final StringBuilder password) {
        List<Character> characters = password.toString().chars()
            .mapToObj(i -> (char)i)
            .collect(Collectors.toList());
        Collections.shuffle(characters);
        StringBuilder result = new StringBuilder(characters.size());
        characters.forEach(result::append);

        return result.toString();
    }

    private void addValidCharacters(final int specialCharacterLength, final int passwordLength,
        final StringBuilder password) {
        for (int i = specialCharacterLength; i < passwordLength; i++) {
            int index = random.nextInt(VALID_CHARACTERS.length());
            password.append(VALID_CHARACTERS.charAt(index));
        }
    }

    private int addSpecialCharacters(final StringBuilder password) {
        final int specialCharacterLength = random.nextInt(MAX_SPECIAL_CHARACTER) + 1;

        for (int i = 0; i < specialCharacterLength; i++) {
            password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));
        }

        return specialCharacterLength;
    }
}
