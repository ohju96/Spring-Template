package com.oh.template.app.member.command.domain;

import java.util.Optional;

public interface MemberRepository {

    /**
     * 이메일로 멤버 존재 여부 조회
     * @param req 멤버 객체
     * @return 멤버 존재 여부 - true: 존재, false: 미존재
     */
    boolean existsByEmail(Member req);

    /**
     * 닉네임으로 멤버 존재 여부 조회
     * @param req 멤버 객체
     * @return 멤버 존재 여부 - true: 존재, false: 미존재
     */
    boolean existsByNickname(Member req);

    /**
     * 프로바이더 아이디로 멤버 존재 여부 조회
     * @param req 멤버 객체
     * @return 멤버 존재 여부 - true: 존재, false: 미존재
     */
    boolean existsByProviderId(Member req);

    Member save(Member req);

}
