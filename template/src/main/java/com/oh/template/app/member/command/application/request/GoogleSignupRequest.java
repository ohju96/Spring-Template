package com.oh.template.app.member.command.application.request;

public record GoogleSignupRequest (
        String email,
        String providerId,
        String nickname,
        String name,
        String password
) {
}
