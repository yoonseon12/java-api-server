package io.study.springbootlayered.api.member.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Embeddable
@Getter
@EqualsAndHashCode
public class MemberPassword {

    @Column(name = "password", nullable = false)
    private String value;

    protected MemberPassword() {
    }

    public MemberPassword(String value) {
        this.value = value;
    }

    public boolean match(String password) {
        return this.value.equals(password);
    }
}
