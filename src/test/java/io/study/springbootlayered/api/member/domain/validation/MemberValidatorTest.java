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

import io.study.springbootlayered.api.member.domain.dto.MemberSignupDto;
import io.study.springbootlayered.api.member.infrastructure.MemberQueryRepository;
import io.study.springbootlayered.web.exception.ApiException;
import io.study.springbootlayered.web.exception.error.MemberErrorCode;

@ExtendWith(MockitoExtension.class)
class MemberValidatorTest {

    @InjectMocks
    private MemberValidator memberValidator;

    @Mock
    private MemberQueryRepository memberQueryRepository;

    @Test
    @DisplayName("중복 이메일 검증 테스트")
    void emailDuplicateValidation() throws Exception {
        //given
        var request = new MemberSignupDto.Command("yoon3@gmail.com", "yy", "1234");
        given(memberQueryRepository.existsByEmail(request.getEmail())).willReturn(true);

        //when & then
        ApiException exception = assertThrows(ApiException.class,
            () -> memberValidator.signinValidate(request));
        assertThat(exception.getErrorCode()).isEqualTo(MemberErrorCode.CONFLICT_DUPLICATE_EMAIL);
    }

    @Test
    @DisplayName("중복 닉네임 검증 테스트")
    void nicknameDuplicateValidation() throws Exception {
        //given
        var request = new MemberSignupDto.Command("yoon3@gmail.com", "yy", "1234");
        given(memberQueryRepository.existsByNickname(request.getNickname())).willReturn(true);

        //when & then
        ApiException exception = assertThrows(ApiException.class,
            () -> memberValidator.signinValidate(request));
        assertThat(exception.getErrorCode()).isEqualTo(MemberErrorCode.CONFLICT_DUPLICATE_NICKNAME);
    }

}