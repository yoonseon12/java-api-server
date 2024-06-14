package io.study.springbootlayered.api.member.ui.mapstruct;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import io.study.springbootlayered.api.member.domain.dto.MemberSigninInternalDto;
import io.study.springbootlayered.api.member.domain.dto.MemberSignupInternalDto;
import io.study.springbootlayered.api.member.ui.dto.MemberSigninExternalDto;
import io.study.springbootlayered.api.member.ui.dto.MemberSignupExternalDto;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface MemberDtoMapstructManager {
    MemberSignupInternalDto.Request of(MemberSignupExternalDto.Request request);

    MemberSignupExternalDto.Response of(MemberSignupInternalDto.Response response);

    MemberSigninInternalDto.Request of(MemberSigninExternalDto.Request request);

    MemberSigninExternalDto.Response of(MemberSigninInternalDto.Response response);
}
