package com.cooba.enums;

import lombok.Getter;

import java.util.List;

import static com.cooba.enums.Authority.*;

@Getter
public enum Role {
    USER("ROLE_USER",List.of(CREATE, SELECT)),
    ADMIN("ROLE_ADMIN",List.of(CREATE, SELECT, UPDATE, DELETE));

    private final String type;
    private final List<Authority> authorities;

    Role(String type, List<Authority> authorities) {
        this.type = type;
        this.authorities = authorities;
    }
}
