package com.oh.template.app.member.command.application.request;

public record NaverSignupRequest (
        String email,
        String providerId,
        String nickname,
        String name,
        String password
) {
}
