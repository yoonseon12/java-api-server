package io.study.springbootlayered.api.member.infrastructure;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import io.study.springbootlayered.api.member.domain.entity.Member;

public interface MemberQueryJpaRepository extends Repository<Member, Long> {

    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);

    Optional<Member> findById(Long id);

}
