package io.study.springbootlayered.api.member.ui;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.study.springbootlayered.api.member.application.MemberSigninService;
import io.study.springbootlayered.api.member.application.MemberSignupService;
import io.study.springbootlayered.api.member.domain.dto.MemberSigninInternalDto;
import io.study.springbootlayered.api.member.domain.dto.MemberSignupInternalDto;
import io.study.springbootlayered.api.member.ui.dto.MemberSigninExternalDto;
import io.study.springbootlayered.api.member.ui.dto.MemberSignupExternalDto;
import io.study.springbootlayered.api.member.ui.mapstruct.MemberDtoMapstructManager;
import io.study.springbootlayered.web.base.BaseResource;
import io.study.springbootlayered.web.base.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
public class MemberController extends BaseResource {

    private final MemberDtoMapstructManager memberDtoMapstructManager;
    private final MemberSignupService memberSignupService;
    private final MemberSigninService memberSigninService;

    /**
     * 유저 회원가입
     *
     * @param externalRequest - 회원 가입 정보
     */
    @PostMapping(value = "/members", headers = X_API_VERSION)
    public ResponseEntity<CommonResponse<MemberSignupExternalDto.Response>> signup(
        @RequestBody @Valid final MemberSignupExternalDto.Request externalRequest) {
        MemberSignupInternalDto.Request internalRequest = memberDtoMapstructManager.of(externalRequest);
        MemberSignupInternalDto.Response internalResponse = memberSignupService.signup(internalRequest);
        MemberSignupExternalDto.Response externalresponse = memberDtoMapstructManager.of(internalResponse);
        return ResponseEntity.ok(CommonResponse.of(externalresponse));
    }

    /**
     * 유저 로그인
     *
     * @param externalRequest - 로그인 정보
     */
    @PostMapping(value = "/members/signin", headers = X_API_VERSION)
    public ResponseEntity<CommonResponse<MemberSigninExternalDto.Response>> signin(
        @RequestBody @Valid final MemberSigninExternalDto.Request externalRequest) {
        MemberSigninInternalDto.Request internalRequest = memberDtoMapstructManager.of(externalRequest);
        MemberSigninInternalDto.Response internalResponse = memberSigninService.signin(internalRequest);
        MemberSigninExternalDto.Response externalresponse = memberDtoMapstructManager.of(internalResponse);
        return ResponseEntity.ok(CommonResponse.of(externalresponse));
    }

}
