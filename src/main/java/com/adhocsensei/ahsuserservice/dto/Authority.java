package com.adhocsensei.ahsuserservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long authorityId;
    private Long userId;
    private String authority;

    public Authority() {
    }

    public Authority(Long authorityId, Long userId, String authority) {
        this.authorityId = authorityId;
        this.userId = userId;
        this.authority = authority;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority1 = (Authority) o;
        return Objects.equals(getAuthorityId(), authority1.getAuthorityId()) && Objects.equals(getUserId(), authority1.getUserId()) && Objects.equals(getAuthority(), authority1.getAuthority());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthorityId(), getUserId(), getAuthority());
    }

    @Override
    public String toString() {
        return "Authority{" +
                "authorityId=" + authorityId +
                ", userId=" + userId +
                ", authority='" + authority + '\'' +
                '}';
    }
}
