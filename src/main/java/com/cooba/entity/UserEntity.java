package com.cooba.entity;

import com.cooba.enums.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class UserEntity implements UserDetails {
    private final String username;
    private final String password;
    private final Role role;
    private final Boolean enabled;
    private final Set<GrantedAuthority> authorities;

    public UserEntity(String username, String password, Role role, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;

        this.authorities = role.getAuthorities().stream()
                .map(authority -> new UserAuthority(username, authority.name()))
                .collect(Collectors.toSet());
        this.authorities.add(new UserAuthority(username, role.getType()));
    }


    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
