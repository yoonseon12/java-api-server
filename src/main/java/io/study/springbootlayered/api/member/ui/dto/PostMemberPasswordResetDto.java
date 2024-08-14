package io.study.springbootlayered.api.member.ui.dto;

import java.beans.ConstructorProperties;

import io.study.springbootlayered.web.annotation.ValidEmail;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostMemberPasswordResetDto {

    @Getter
    public static class Request {
        @ValidEmail
        private final String email;

        @ConstructorProperties(value = {"email"})
        public Request(final String email) {
            this.email = email;
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class Response {
        private final String password;
    }

}
