package com.oh.template.config.jwt;

import com.oh.template.app.member.command.domain.Role;
import org.springframework.security.core.Authentication;

import java.util.Map;

public interface JwtProvider <T> {
    T convertAuthToken(String token);

    Authentication getAuthentication(T authToken);

    T createAccessToken(String userId, Role role, Map<String, Object> claims);

    T createRefreshToken(String userId, Role role, Map<String, Object> claims);
}
