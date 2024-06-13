package io.study.springbootlayered.api.member.domain.entity;

import io.study.springbootlayered.web.base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member_authority")
public class MemberAuthority extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Enumerated(EnumType.STRING)
    private AuthorityType authority;

    private MemberAuthority(Member member, AuthorityType authority) {
        this.member = member;
        this.authority = authority;
    }

    public static MemberAuthority setRoleUser(Member member) {
        return new MemberAuthority(member, AuthorityType.ROLE_USER);
    }

    public void addMember(Member member) {
        this.member = member;
    }

}
