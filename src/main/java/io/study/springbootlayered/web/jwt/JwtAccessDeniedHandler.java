package io.study.springbootlayered.web.jwt;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import io.study.springbootlayered.web.base.response.ErrorResponse;
import io.study.springbootlayered.web.exception.error.LoginErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 필요한 권한이 존재하지 않을 경우 403 Forbidden 에러를 반환한다.
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(LoginErrorCode.FORBIDDEN.getHttpStatus().value());
        response.getWriter().println(ErrorResponse.of(LoginErrorCode.FORBIDDEN));
    }
}
