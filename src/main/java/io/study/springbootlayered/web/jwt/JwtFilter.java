package io.study.springbootlayered.web.jwt;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import io.study.springbootlayered.web.exception.error.LoginErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private static final String ATTRIBUTE = "token_exception";
    private static final String TOKEN_PREFIX = "Bearer";
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        String jwt = resolveToken(request);
        validateToken(request, jwt);

        if (StringUtils.hasText(jwt) && jwtProvider.validateAccessToken(jwt)) {
            Authentication authentication = jwtProvider.getAuthentication(jwt);
            log.info("인증정보 저장 {}", authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private void validateToken(final HttpServletRequest request, final String token) {
        try {
            jwtProvider.validateAccessToken(token);
        } catch (SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
            request.setAttribute(ATTRIBUTE, LoginErrorCode.INVALID_JWT_SIGNATURE);
        } catch (ExpiredJwtException e) {
            request.setAttribute(ATTRIBUTE, LoginErrorCode.EXPIRED_JWT_TOKEN);
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
            request.setAttribute(ATTRIBUTE, LoginErrorCode.UNSUPPORTED_JWT_TOKEN);
        } catch (IllegalArgumentException e) {
            log.error("JWT 토큰이 잘못되었습니다.");
            request.setAttribute(ATTRIBUTE, LoginErrorCode.INVALID_JWT_TOKEN);
        } catch (Exception e) {
            log.error("================================================");
            log.error("JwtFilter - doFilterInternal() 오류발생");
            log.error("token : {}", token);
            log.error("Exception Message : {}", e.getMessage());
            log.error("Exception StackTrace : {");
            e.printStackTrace();
            log.error("}");
            log.error("================================================");
            request.setAttribute(ATTRIBUTE, LoginErrorCode.JWT_UNKNOWN_ERROR);
        }
    }

    private String resolveToken(final HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isExistBearer(bearerToken)) {
            return bearerToken.substring(TOKEN_PREFIX.length()).trim();
        }
        return null;
    }

    private boolean isExistBearer(final String bearerToken) {
        return StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX);
    }
}
