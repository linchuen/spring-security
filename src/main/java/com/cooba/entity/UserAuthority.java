package com.cooba.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public class UserAuthority implements GrantedAuthority {
    private final String username;
    private final String authority;

    public UserAuthority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
