package io.study.springbootlayered.api.member.domain.repository;

import io.study.springbootlayered.api.member.domain.entity.Member;

public interface MemberRepository {

    Member save(Member member);

}
