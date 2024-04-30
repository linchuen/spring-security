package com.cooba.service;

import com.cooba.entity.UserAuthority;
import com.cooba.entity.UserEntity;
import com.cooba.enums.Role;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TokenServiceTest {
    @InjectMocks
    TokenService tokenService;

    @Test
    void generateToken() {
        UserEntity root = new UserEntity("root","root",Role.ADMIN,true);

        String token = tokenService.generateToken(root);
        Assertions.assertNotNull(token);

        Claims claims = tokenService.parseToken(token);
        Assertions.assertEquals("root", claims.get("name"));
    }
}