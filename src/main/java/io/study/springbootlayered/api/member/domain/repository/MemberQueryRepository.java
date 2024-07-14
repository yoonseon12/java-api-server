package io.study.springbootlayered.api.member.domain.repository;

import java.util.Optional;

import io.study.springbootlayered.api.member.domain.entity.Member;

public interface MemberQueryRepository {
    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);

    Optional<Member> findById(Long id);
}
