package com.oh.template.app.member.command.domain;

import com.oh.template.app.common.command.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Table(name = "member")
@Entity
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String email;

    private String providerId;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    private String nickname;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
