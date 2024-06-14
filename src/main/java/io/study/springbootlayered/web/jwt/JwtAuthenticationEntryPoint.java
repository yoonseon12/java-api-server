package io.study.springbootlayered.web.jwt;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import io.study.springbootlayered.web.base.response.ErrorResponse;
import io.study.springbootlayered.web.exception.error.LoginErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 유효한 자격증명을 제공하지 않고 접근할 때 401 Unauthorized 에러를 반환
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(LoginErrorCode.UNAUTHORIZED.getHttpStatus().value());
        response.getWriter().println(
            ErrorResponse.of(LoginErrorCode.UNAUTHORIZED));
    }
}
