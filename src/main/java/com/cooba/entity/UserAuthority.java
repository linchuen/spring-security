package com.cooba.entity;

import com.cooba.enums.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public class UserAuthority implements GrantedAuthority {
    private final String username;
    private final Role role;

    public UserAuthority(String username, Role role) {
        this.username = username;
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.name();
    }
}
