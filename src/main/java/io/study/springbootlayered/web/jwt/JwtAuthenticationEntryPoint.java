package io.study.springbootlayered.web.jwt;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

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

    private static final String ATTRIBUTE = "token_exception";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {

        LoginErrorCode attribute = (LoginErrorCode)request.getAttribute(ATTRIBUTE);

        switch (attribute) {
            case INVALID_JWT_SIGNATURE:
                responseBuilder(response, LoginErrorCode.INVALID_JWT_SIGNATURE);
                return;
            case EXPIRED_JWT_TOKEN:
                responseBuilder(response, LoginErrorCode.EXPIRED_JWT_TOKEN);
                return;
            case UNSUPPORTED_JWT_TOKEN:
                responseBuilder(response, LoginErrorCode.UNSUPPORTED_JWT_TOKEN);
                return;
            case INVALID_JWT_TOKEN:
                responseBuilder(response, LoginErrorCode.INVALID_JWT_TOKEN);
                return;
            case JWT_UNKNOWN_ERROR:
                responseBuilder(response, LoginErrorCode.JWT_UNKNOWN_ERROR);
                return;
            default:
                responseBuilder(response, LoginErrorCode.JWT_UNKNOWN_ERROR);
                return;
        }
    }

    private void responseBuilder(HttpServletResponse response, LoginErrorCode errorCode) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().println(
            objectMapper.writeValueAsString(
                ErrorResponse.of(errorCode)
            )
        );
    }
}
