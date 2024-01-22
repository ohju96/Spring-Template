package com.oh.template.app.member.infra;

import com.oh.template.app.member.command.domain.MemberJPARepository;
import com.oh.template.app.member.command.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJPARepository memberJPARepository;
}
