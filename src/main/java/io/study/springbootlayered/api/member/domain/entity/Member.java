package io.study.springbootlayered.api.member.domain.entity;

import java.util.HashSet;
import java.util.Set;

import io.study.springbootlayered.web.base.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private MemberPassword password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatusType memberStatus;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MemberAuthority> authorities = new HashSet<>();

    private Member(final String email, final String nickname, final String password) {
        this.password = new MemberPassword(password);
        this.memberStatus = MemberStatusType.ACTIVE;
        this.email = email;
        this.nickname = nickname;
    }

    public static Member createBasicMember(final String email, final String nickname, final String password) {
        Member member = new Member(email, nickname, password);
        member.addAuthority(MemberAuthority.setRoleUser(member));
        return member;
    }

    private void addAuthority(final MemberAuthority memberAuthority) {
        authorities.add(memberAuthority);
        memberAuthority.addMember(this);
    }

    public void changePassword(final String encryptPassword) {
        this.password = new MemberPassword(encryptPassword);
    }

}
