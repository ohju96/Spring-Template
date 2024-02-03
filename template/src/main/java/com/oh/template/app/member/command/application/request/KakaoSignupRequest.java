package com.oh.template.app.member.command.application.request;

public record KakaoSignupRequest (
        String email,
        String providerId,
        String nickname,
        String name,
        String password
) {
}
