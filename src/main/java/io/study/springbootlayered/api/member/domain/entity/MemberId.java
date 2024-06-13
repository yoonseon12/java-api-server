package io.study.springbootlayered.api.member.domain.entity;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Embeddable
public class MemberId {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public MemberId(Long id) {
        this.id = id;
    }

    protected MemberId() {
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MemberId memberId = (MemberId)o;
        return Objects.equals(id, memberId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
