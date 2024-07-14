package io.study.springbootlayered.api.member.infrastructure;

import org.springframework.data.repository.Repository;

import io.study.springbootlayered.api.member.domain.entity.Member;
import io.study.springbootlayered.api.member.domain.entity.MemberId;

public interface MemberJpaRepository extends Repository<Member, MemberId> {

    Member save(Member member);

}
