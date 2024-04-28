package com.cooba.service;

import com.cooba.entity.UserAuthority;
import com.cooba.entity.UserEntity;
import com.cooba.enums.Role;
import com.cooba.request.RegisterRequest;
import com.cooba.response.RegisterResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class MapUserDetailsService implements UserDetailsService {
    private final Map<String, UserEntity> userDetailsMap = new ConcurrentHashMap<>();
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;


    @PostConstruct
    public void initUser() {
        UserEntity root = new UserEntity();
        root.setUsername("root");
        root.setPassword("root");
        root.setAuthorities(List.of(new UserAuthority("root", Role.ADMIN)));
        root.setEnabled(true);

        userDetailsMap.put(root.getUsername(), root);
        String token = tokenService.generateToken(root);
        log.info("Root token: {}", token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!userDetailsMap.containsKey(username)) throw new UsernameNotFoundException(username);

        return userDetailsMap.get(username);
    }

    public RegisterResponse register(RegisterRequest request) {
        String username = request.getUsername();
        String password = passwordEncoder.encode(request.getPassword());

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        user.setAuthorities(List.of(new UserAuthority(username, Role.USER)));
        user.setEnabled(true);
        userDetailsMap.put(username, user);

        String token = tokenService.generateToken(user);
        RegisterResponse response = new RegisterResponse();
        response.setToken(token);
        return response;
    }
}
