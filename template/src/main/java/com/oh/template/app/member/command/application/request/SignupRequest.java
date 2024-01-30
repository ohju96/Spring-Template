package com.oh.template.app.member.command.application.request;

import com.oh.template.app.member.command.domain.Provider;

public record SignupRequest (
        String email,
        String providerId,
        Provider provider,
        String nickname,
        String name,
        String password
) {
}
