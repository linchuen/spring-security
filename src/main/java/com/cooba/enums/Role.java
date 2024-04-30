package com.cooba.enums;

import lombok.Getter;

import java.util.List;

import static com.cooba.enums.Authority.*;

@Getter
public enum Role {
    USER("role_user",List.of(CREATE, SELECT)),
    ADMIN("role_admin",List.of(CREATE, SELECT, UPDATE, DELETE));

    private final String type;
    private final List<Authority> authorities;

    Role(String type, List<Authority> authorities) {
        this.type = type;
        this.authorities = authorities;
    }
}
