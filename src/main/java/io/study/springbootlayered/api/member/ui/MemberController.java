package io.study.springbootlayered.api.member.ui;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.study.springbootlayered.api.member.application.MemberDetailUseCase;
import io.study.springbootlayered.api.member.application.MemberResetUseCase;
import io.study.springbootlayered.api.member.application.MemberSigninUseCase;
import io.study.springbootlayered.api.member.application.MemberSignupUseCase;
import io.study.springbootlayered.api.member.domain.dto.MemberDetailDto;
import io.study.springbootlayered.api.member.domain.dto.MemberPasswordResetDto;
import io.study.springbootlayered.api.member.domain.dto.MemberSigninDto;
import io.study.springbootlayered.api.member.domain.dto.MemberSignupDto;
import io.study.springbootlayered.api.member.ui.dto.GetMemberDetailDto;
import io.study.springbootlayered.api.member.ui.dto.PostMemberPasswordResetDto;
import io.study.springbootlayered.api.member.ui.dto.PostMemberSigninDto;
import io.study.springbootlayered.api.member.ui.dto.PostMemberSignupDto;
import io.study.springbootlayered.api.member.ui.mapstruct.MemberDtoMapstructMapper;
import io.study.springbootlayered.web.annotation.OnlyOwnerAllowed;
import io.study.springbootlayered.web.base.BaseResource;
import io.study.springbootlayered.web.base.response.BaseResponse;
import io.study.springbootlayered.web.base.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController extends BaseResource {

    private final MemberDtoMapstructMapper memberDtoMapstructManager;
    private final MemberSignupUseCase memberSignupUseCase;
    private final MemberSigninUseCase memberSigninUseCase;
    private final MemberDetailUseCase memberDetailUseCase;
    private final MemberResetUseCase memberResetUseCase;

    /**
     * 유저 회원가입
     *
     * @param request - 회원 가입 정보
     */
    @PostMapping(value = "/members", headers = X_API_VERSION)
    public ResponseEntity<CommonResponse<PostMemberSignupDto.Response>> signup(
        @RequestBody @Valid final PostMemberSignupDto.Request request) {
        MemberSignupDto.Command command = memberDtoMapstructManager.of(request);
        MemberSignupDto.Info info = memberSignupUseCase.signup(command);
        PostMemberSignupDto.Response response = memberDtoMapstructManager.of(info);
        return ResponseEntity.ok(CommonResponse.of(response));
    }

    /**
     * 유저 로그인
     *
     * @param request - 로그인 정보
     */
    @PostMapping(value = "/members/signin", headers = X_API_VERSION)
    public ResponseEntity<CommonResponse<PostMemberSigninDto.Response>> signin(
        @RequestBody @Valid final PostMemberSigninDto.Request request) {
        MemberSigninDto.Command command = memberDtoMapstructManager.of(request);
        MemberSigninDto.Info info = memberSigninUseCase.signin(command);
        PostMemberSigninDto.Response response = memberDtoMapstructManager.of(info);
        return ResponseEntity.ok(CommonResponse.of(response));
    }

    /**
     * 유저 정보 확인
     *
     * @param memberId - 회원 식별 키
     */
    @GetMapping(value = "/members/{memberId}", headers = X_API_VERSION)
    @OnlyOwnerAllowed
    public ResponseEntity<CommonResponse<GetMemberDetailDto.Response>> detail(@PathVariable final Long memberId) {
        MemberDetailDto.Info info = memberDetailUseCase.detail(memberId);
        GetMemberDetailDto.Response response = memberDtoMapstructManager.of(info);
        return ResponseEntity.ok(CommonResponse.of(response));
    }

    /**
     * 비밀번호 초기화
     *
     * @param request - 회원 정보
     */
    @PostMapping(value = "/members/reset-password", headers = X_API_VERSION)
    public ResponseEntity<BaseResponse> resetPassword(
        @RequestBody @Valid final PostMemberPasswordResetDto.Request request) {
        MemberPasswordResetDto.Command command = memberDtoMapstructManager.of(request);
        memberResetUseCase.resetPassword(command);
        return ResponseEntity.ok(new BaseResponse());
    }
}
