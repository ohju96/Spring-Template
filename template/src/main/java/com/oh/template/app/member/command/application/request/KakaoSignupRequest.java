package com.oh.template.app.member.command.application.request;

import lombok.Builder;

@Builder
public record KakaoSignupRequest (
        String email,
        String providerId,
        String nickname,
        String name,
        String password
) {
    public static KakaoSignupRequest of(String email, String providerId, String nickname, String name, String password) {
        return builder()
                .email(email)
                .providerId(providerId)
                .nickname(nickname)
                .name(name)
                .password(password)
                .build();
    }
}
