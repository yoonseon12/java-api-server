package io.study.springbootlayered.api.member.domain.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignupEvent {

    private final String email;

    public static SignupEvent of(String email) {
        return new SignupEvent(email);
    }
}
