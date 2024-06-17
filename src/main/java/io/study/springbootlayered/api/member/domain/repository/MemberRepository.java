package io.study.springbootlayered.api.member.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import io.study.springbootlayered.api.member.domain.entity.Member;
import io.study.springbootlayered.api.member.domain.entity.MemberId;

public interface MemberRepository extends Repository<Member, MemberId> {
    Member save(Member member);

    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);

    Optional<Member> findById(Long id);

}
