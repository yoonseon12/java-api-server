package io.study.springbootlayered.web.jwt;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.study.springbootlayered.web.base.response.ErrorResponse;
import io.study.springbootlayered.web.exception.error.AuthErrorCode;
import io.study.springbootlayered.web.exception.error.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 필요한 권한이 존재하지 않을 경우 403 Forbidden 에러를 반환한다.
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException, ServletException {
        responseBuilder(response, AuthErrorCode.FORBIDDEN_REQUEST);
    }

    private void responseBuilder(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().print(
            objectMapper.writeValueAsString(
                ErrorResponse.of(errorCode)
            )
        );
    }
}
