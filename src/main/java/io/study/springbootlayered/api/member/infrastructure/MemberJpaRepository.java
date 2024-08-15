package io.study.springbootlayered.api.member.infrastructure;

import org.springframework.data.repository.Repository;

import io.study.springbootlayered.api.member.domain.entity.Member;

public interface MemberJpaRepository extends Repository<Member, Long> {

    Member save(Member member);

}
