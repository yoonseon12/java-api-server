package io.study.springbootlayered.api.member.domain.entity;

public enum MemberStatusType {
    ACTIVE("활성화"),
    INACTIVE("비활성화");

    MemberStatusType(String status) {
        this.status = status;
    }
    private final String status;

    public String getStatus() {
        return status;
    }
}
