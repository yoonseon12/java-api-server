package io.study.springbootlayered.api.member.ui;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.study.springbootlayered.api.member.application.MemberSigninService;
import io.study.springbootlayered.api.member.application.MemberSignupService;
import io.study.springbootlayered.api.member.domain.dto.MemberSigninDto;
import io.study.springbootlayered.api.member.domain.dto.MemberSignupDto;
import io.study.springbootlayered.api.member.ui.dto.PostMemberSigninDto;
import io.study.springbootlayered.api.member.ui.dto.PostMemberSignupDto;
import io.study.springbootlayered.api.member.ui.mapstruct.MemberDtoMapstructMapper;
import io.study.springbootlayered.web.base.BaseResource;
import io.study.springbootlayered.web.base.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
public class MemberController extends BaseResource {

    private final MemberDtoMapstructMapper memberDtoMapstructManager;
    private final MemberSignupService memberSignupService;
    private final MemberSigninService memberSigninService;

    /**
     * 유저 회원가입
     *
     * @param request - 회원 가입 정보
     */
    @PostMapping(value = "/members", headers = X_API_VERSION)
    public ResponseEntity<CommonResponse<PostMemberSignupDto.Response>> signup(
        @RequestBody @Valid final PostMemberSignupDto.Request request) {
        MemberSignupDto.Command command = memberDtoMapstructManager.of(request);
        MemberSignupDto.Info info = memberSignupService.signup(command);
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
        MemberSigninDto.Info info = memberSigninService.signin(command);
        PostMemberSigninDto.Response response = memberDtoMapstructManager.of(info);
        return ResponseEntity.ok(CommonResponse.of(response));
    }


    /*
    1. DTO 이름 양식 정해야하고
    2. 로그인 후 토큰까서 정보 잘 들어가있는지 확인하고 - ok
    3. @Value 어노테이션 제거
    4. 이메일이랑 비밀번호 어노테이션 만들어서 달기
    5. 회원정보 조회 API 구현(로그인 테스트 용)
     */

}
