package io.study.springbootlayered.api.member.domain.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResetPasswordEvent {

    private final String email;
    private final String tempPassword;

    public static ResetPasswordEvent of(final String email, final String password) {
        return new ResetPasswordEvent(email, password);
    }
}
