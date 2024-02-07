package com.oh.template.app.member.infra;

import com.oh.template.app.member.command.domain.Member;
import com.oh.template.app.member.command.domain.Provider;
import com.oh.template.app.member.command.domain.Role;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberRepositoryImplTest {

    @Autowired
    private MemberRepositoryImpl memberRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void save() throws Exception {
        //given
        Member member = Member.builder()
                .email("test@test.com")
                .provider(Provider.NONE)
                .providerId(null)
                .password("sample123pwd")
                .nickname("테스트닉네임")
                .name("테스트")
                .role(Role.USER)
                .build();

        //when
        Member save = memberRepository.save(member);

        //then
        Assertions.assertThat(save).isNotNull();
    }

}