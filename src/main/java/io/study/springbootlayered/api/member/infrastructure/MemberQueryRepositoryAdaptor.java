package io.study.springbootlayered.api.member.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Component;

import io.study.springbootlayered.api.member.domain.entity.Member;
import io.study.springbootlayered.api.member.domain.repository.MemberQueryRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberQueryRepositoryAdaptor implements MemberQueryRepository {

    private final MemberQueryJpaRepository memberQueryJpaRepository;

    @Override
    public boolean existsByNickname(final String nickname) {
        return memberQueryJpaRepository.existsByNickname(nickname);
    }

    @Override
    public boolean existsByEmail(final String email) {
        return memberQueryJpaRepository.existsByEmail(email);
    }

    @Override
    public Optional<Member> findByEmail(final String email) {
        return memberQueryJpaRepository.findByEmail(email);
    }

    @Override
    public Optional<Member> findById(final Long id) {
        return memberQueryJpaRepository.findById(id);
    }
}
