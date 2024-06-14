package io.study.springbootlayered.web.jwt;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import io.study.springbootlayered.web.exception.error.LoginErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {
    private static final String ATTRIBUTE = "token_exception";
    private static final String TOKEN_PREFIX = "Bearer";
    private final JwtProvider jwtProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
        IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        String jwt = resolveToken(httpServletRequest);

        try {
            jwtProvider.validateAccessToken(jwt);
        } catch (SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
            servletRequest.setAttribute(ATTRIBUTE, LoginErrorCode.INVALID_JWT_SIGNATURE);
        } catch (ExpiredJwtException e) {
            servletRequest.setAttribute(ATTRIBUTE, LoginErrorCode.EXPIRED_JWT_TOKEN);
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
            servletRequest.setAttribute(ATTRIBUTE, LoginErrorCode.UNSUPPORTED_JWT_TOKEN);
        } catch (IllegalArgumentException e) {
            log.error("JWT 토큰이 잘못되었습니다.");
            servletRequest.setAttribute(ATTRIBUTE, LoginErrorCode.INVALID_JWT_TOKEN);
        } catch (Exception e) {
            log.error("================================================");
            log.error("JwtFilter - doFilterInternal() 오류발생");
            log.error("token : {}", jwt);
            log.error("Exception Message : {}", e.getMessage());
            log.error("Exception StackTrace : {");
            e.printStackTrace();
            log.error("}");
            log.error("================================================");
            servletRequest.setAttribute(ATTRIBUTE, LoginErrorCode.JWT_UNKNOWN_ERROR);
        }

        if (StringUtils.hasText(jwt) && jwtProvider.validateAccessToken(jwt)) {
            Authentication authentication = jwtProvider.getAuthentication(jwt);
            log.info("인증정보 저장 {}", authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String resolveToken(final HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isExistBearer(bearerToken)) {
            return bearerToken.substring(TOKEN_PREFIX.length()).trim();
        }
        return null;
    }

    private boolean isExistBearer(String bearerToken) {
        return StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX);
    }
}
