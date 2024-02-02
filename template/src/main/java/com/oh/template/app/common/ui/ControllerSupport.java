package com.oh.template.app.common.ui;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class ControllerSupport {
    final String ANONYMOUS_USER = "anonymousUser";

    private Optional<Authentication> getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
    }

    public String getUserIdByAuthentication() {
        return getAuthentication()
                .map(Authentication::getName)
                .filter(name -> !ANONYMOUS_USER.equals(name))
                .orElseThrow(() -> new RuntimeException("사용자 아이디를 찾을 수 없습니다."));
    }
}
