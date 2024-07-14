package io.study.springbootlayered.api.member.domain;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.study.springbootlayered.api.member.domain.dto.MemberSignupDto;
import io.study.springbootlayered.api.member.domain.entity.Member;
import io.study.springbootlayered.api.member.domain.repository.MemberRepository;
import io.study.springbootlayered.api.member.domain.validation.MemberValidator;

@ExtendWith(MockitoExtension.class)
class MemberProcessorImplTest {

    @InjectMocks
    private MemberProcessorImpl memberProcessorImpl;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private MemberValidator memberValidator;

    @Test
    @DisplayName("회원가입 성공 테스트")
    void signupSuccess() throws Exception {
        //given
        var request = new MemberSignupDto.Command("kkk@gmail.com", "kkk", "1234");
        Member member = mockMember();
        given(memberRepository.save(any(Member.class))).willReturn(member);

        //when
        var response = memberProcessorImpl.register(request);

        //then
        assertThat(response).isNotNull();
        then(memberRepository).should(times(1)).save(any(Member.class));
        then(memberValidator).should(times(1)).signinValidate(request);

    }

    private Member mockMember() {
        return Member.createBasicMember("kkk@gmail.com", "kkk", "1234");
    }
}