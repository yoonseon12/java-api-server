package io.study.springbootlayered.api.member.domain.validation;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.study.springbootlayered.api.member.domain.dto.MemberSignupInternalDto;
import io.study.springbootlayered.api.member.domain.repository.MemberRepository;
import io.study.springbootlayered.web.exception.ApiException;
import io.study.springbootlayered.web.exception.error.MemberErrorCode;

@ExtendWith(MockitoExtension.class)
class MemberValidatorTest {

    @InjectMocks
    private MemberValidator memberValidator;

    @Mock
    private MemberRepository memberRepository;

    @Test
    @DisplayName("중복 이메일 검증 테스트")
    void emailDuplicateValidation() throws Exception {
        //given
        var request = new MemberSignupInternalDto.Request("yoon3@gmail.com", "yy", "1234");
        given(memberRepository.existsByEmail(request.getEmail())).willReturn(true);

        //when & then
        ApiException exception = assertThrows(ApiException.class,
            () -> memberValidator.validate(request));
        assertThat(exception.getErrorCode()).isEqualTo(MemberErrorCode.CONFLICT_DUPLICATE_EMAIL);
    }

    @Test
    @DisplayName("중복 닉네임 검증 테스트")
    void nicknameDuplicateValidation() throws Exception {
        //given
        var request = new MemberSignupInternalDto.Request("yoon3@gmail.com", "yy", "1234");
        given(memberRepository.existsByNickname(request.getNickname())).willReturn(true);

        //when & then
        ApiException exception = assertThrows(ApiException.class,
            () -> memberValidator.validate(request));
        assertThat(exception.getErrorCode()).isEqualTo(MemberErrorCode.CONFLICT_DUPLICATE_NICKNAME);
    }

}