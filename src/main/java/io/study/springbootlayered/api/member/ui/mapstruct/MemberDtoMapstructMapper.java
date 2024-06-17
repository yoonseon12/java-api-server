package io.study.springbootlayered.api.member.ui.mapstruct;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import io.study.springbootlayered.api.member.domain.dto.MemberDetailDto;
import io.study.springbootlayered.api.member.domain.dto.MemberSigninDto;
import io.study.springbootlayered.api.member.domain.dto.MemberSignupDto;
import io.study.springbootlayered.api.member.ui.dto.GetMemberDetailDto;
import io.study.springbootlayered.api.member.ui.dto.PostMemberSigninDto;
import io.study.springbootlayered.api.member.ui.dto.PostMemberSignupDto;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface MemberDtoMapstructMapper {

    MemberSignupDto.Command of(PostMemberSignupDto.Request request);

    PostMemberSignupDto.Response of(MemberSignupDto.Info response);

    MemberSigninDto.Command of(PostMemberSigninDto.Request request);

    PostMemberSigninDto.Response of(MemberSigninDto.Info response);

    GetMemberDetailDto.Response of(MemberDetailDto.Info response);
}
