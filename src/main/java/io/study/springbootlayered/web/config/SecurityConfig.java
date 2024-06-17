package io.study.springbootlayered.web.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import io.study.springbootlayered.web.jwt.JwtAccessDeniedHandler;
import io.study.springbootlayered.web.jwt.JwtAuthenticationEntryPoint;
import io.study.springbootlayered.web.jwt.JwtProvider;
import io.study.springbootlayered.web.jwt.JwtSecurityConfig;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtProvider jwtProvider;

    private static final String[] PERMIT_ALL_POSTS = {
        "/api/members",
        "/api/members/signin"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
            .formLogin(FormLoginConfigurer::permitAll)
            .httpBasic(HttpBasicConfigurer::disable);

        http.sessionManagement(sessionManagement ->
            sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(authorize ->
            authorize
                .requestMatchers(HttpMethod.POST, PERMIT_ALL_POSTS).permitAll()
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .anyRequest().authenticated());

        http.headers(header ->
            header
                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        http.exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler));

        http.apply(new JwtSecurityConfig(jwtProvider));

        return http.build();
    }
}
