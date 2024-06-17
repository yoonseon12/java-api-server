package io.study.springbootlayered.web.jwt;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.study.springbootlayered.web.config.properties.JwtProperties;

@Component
public class JwtProvider {
    private final JwtProperties jwtProperties;
    private static final String AUTHORITIES_KEY = "auth";
    private final Key key;

    public JwtProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenInfo createToken(final Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));

        String accessToken = createAccessToken(authentication.getName(), authorities);
        String refreshToken = createRefreshToken(authentication.getName());

        return TokenInfo.of(accessToken, refreshToken);
    }

    /** Access Token 발급 **/
    public String createAccessToken(final String sub, final String authorities) {
        return Jwts.builder()
            .setSubject(sub)
            .claim(AUTHORITIES_KEY, authorities)
            .setExpiration(getExpirationTime(jwtProperties.getAccessTokenExpireTime()))
            .signWith(key, SignatureAlgorithm.HS512)
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .compact();
    }

    /** Refresh Token 발급 **/
    public String createRefreshToken(final String sub) {
        return Jwts.builder()
            .setSubject(sub)
            .setExpiration(getExpirationTime(jwtProperties.getRefreshTokenExpireTime()))
            .signWith(key, SignatureAlgorithm.HS512)
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .compact();
    }

    private Date getExpirationTime(final long tokenExpireTime) {
        LocalDateTime localDateTime = LocalDateTime.now()
            .plusSeconds(tokenExpireTime);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();

        return Date.from(instant);
    }

    /** 인증 정보 조회 **/
    public Authentication getAuthentication(final String token) {
        Claims claims = parseClaims(token);

        List<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
            .map(SimpleGrantedAuthority::new)
            .toList();

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    private Claims parseClaims(final String accessToken) {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public boolean validateAccessToken(final String token) {
        Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token);

        return true;
    }
}
