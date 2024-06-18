package io.study.springbootlayered.web.base.principal;

import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import io.study.springbootlayered.api.member.domain.entity.AuthorityType;
import io.study.springbootlayered.web.annotation.LoginInfo;

public class AuthenticationPrincipalArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<AuthorityType> roles = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .map(AuthorityType::valueOf)
            .toList();

        return new LoginMemberInfo(authentication.getName(), roles);
    }
}
