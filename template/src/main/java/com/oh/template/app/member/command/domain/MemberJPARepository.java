package com.oh.template.app.member.command.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJPARepository extends JpaRepository<Member, Long> {
}
