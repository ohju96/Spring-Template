package com.oh.template.app.member.infra;

import com.oh.template.app.member.command.domain.Member;
import com.oh.template.app.member.command.domain.MemberJPARepository;
import com.oh.template.app.member.command.domain.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.oh.template.app.member.command.domain.QMember.member;

@Repository
public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepository {

    private final MemberJPARepository memberJPARepository;
    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(
            MemberJPARepository memberJPARepository,
            EntityManager em
    ) {
        super(MemberRepositoryImpl.class);
        this.memberJPARepository = memberJPARepository;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public boolean existsByEmail(Member req) {
        Integer fetch = queryFactory
                .selectOne()
                .from(member)
                .where(member.email.eq(req.getEmail()))
                .fetchFirst();
        return fetch != null; // 1개라도 있으면 true
    }

    @Override
    public boolean existsByNickname(Member req) {
        Integer fetch = queryFactory
                .selectOne()
                .from(member)
                .where(member.nickname.eq(req.getNickname()))
                .fetchFirst();
        return fetch != null; // 1개라도 있으면 true
    }

    @Override
    public boolean existsByProviderId(Member req) {
        Integer fetch = queryFactory
                .selectOne()
                .from(member)
                .where(member.providerId.eq(req.getProviderId()))
                .fetchFirst();
        return fetch != null; // 1개라도 있으면 true
    }

    @Override
    public Member save(Member req) {
        return memberJPARepository.save(req);
    }
}
