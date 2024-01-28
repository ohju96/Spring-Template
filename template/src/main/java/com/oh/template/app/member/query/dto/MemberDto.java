package com.oh.template.app.member.query.dto;

import com.oh.template.app.member.command.domain.Member;
import com.oh.template.app.member.command.domain.Provider;
import com.oh.template.app.member.command.domain.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
@Schema(name = "MemberDto", description = "멤버 DTO")
public record MemberDto (

        @Schema(name = "id", description = "멤버 ID")
        Long id,

        @Schema(name = "email", description = "멤버 이메일")
        String email,

        @Schema(name = "providerId", description = "프로바이더 ID")
        String providerId,

        @Schema(name = "provider", description = "프로바이더(구글, 카카오, 네이버)")
        Provider provider,

        @Schema(name = "nickname", description = "멤버 닉네임")
        String nickname,

        @Schema(name = "name", description = "멤버 이름")
        String name,

        @Schema(name = "password", description = "멤버 비밀번호")
        String password,

        @Schema(name = "role", description = "멤버 권한")
        Role role
) {
    public static Member toEntity(MemberDto memberDto) {
        return Member.builder()
                .id(memberDto.getId())
                .email(memberDto.getEmail())
                .providerId(memberDto.getProviderId())
                .provider(memberDto.getProvider())
                .nickname(memberDto.getNickname())
                .name(memberDto.getName())
                .password(memberDto.getPassword())
                .role(memberDto.getRole())
                .build();
    }

    public static MemberDto of(
            Long id,
            String email,
            String providerId,
            Provider provider,
            String nickname,
            String name,
            String password,
            Role role
    ) {
        return MemberDto.builder()
                .id(id)
                .email(email)
                .providerId(providerId)
                .provider(provider)
                .nickname(nickname)
                .name(name)
                .password(password)
                .role(role)
                .build();
    }
}
