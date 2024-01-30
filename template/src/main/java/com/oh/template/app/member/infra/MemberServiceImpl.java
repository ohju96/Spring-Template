package com.oh.template.app.member.infra;

import com.oh.template.app.common.exception.CustomException;
import com.oh.template.app.member.command.application.MemberService;
import com.oh.template.app.member.command.application.request.SignupRequest;
import com.oh.template.app.member.command.domain.Role;
import com.oh.template.app.member.query.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.oh.template.app.common.exception.ExceptionEnum.*;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepositoryImpl memberRepository;

    @Transactional
    @Override
    public void signup(SignupRequest signupRequest) {

        // MemberDto 생성
        MemberDto memberDto = MemberDto.of(
                null,
                signupRequest.email(),
                signupRequest.providerId(),
                signupRequest.provider(),
                signupRequest.nickname(),
                signupRequest.name(),
                encryptPassword(signupRequest.password()),
                Role.USER
        );

        // 이미 존재하는 회원인지 확인하기
        if (existsByProviderId(memberDto.providerId())) {
            throw new CustomException(ALREADY_EXIST_MEMBER);
        }

        // 이미 존재하는 닉네임인지 확인하기
        if (existsByNickname(memberDto.nickname())) {
            throw new CustomException(ALREADY_EXIST_NICKNAME);
        }

        // 이미 존재하는 이메일인지 확인하기
        if (existsByEmail(memberDto.email())) {
            throw new CustomException(ALREADY_EXIST_EMAIL);
        }


        // 프로바이더 별로 회원가입하기
        switch (memberDto.provider()) {
            case GOOGLE -> signupGoogle(memberDto);
            case KAKAO -> signupKakao(memberDto);
            case NONE -> signupNone(memberDto);
            default -> throw new CustomException(INVALID_PROVIDER);
        }

    }

    /**
     * 구글로 회원가입하기
     * @param memberDto 멤버 DTO
     */
    private void signupGoogle(MemberDto memberDto) {

    }

    /**
     * 카카오로 회원가입하기
     * @param memberDto 멤버 DTO
     */
    private void signupKakao(MemberDto memberDto) {

    }

    /**
     * 일반(이메일) 회원가입하기
     * @param memberDto 멤버 DTO
     */
    private void signupNone(MemberDto memberDto) {

    }

    /**
     * 프로바이더 ID로 회원이 존재하는지 확인하기
     * @param providerId 프로바이더 ID
     * @return 회원이 존재하면 true, 존재하지 않으면 false
     */
    private boolean existsByProviderId(String providerId) {
        return false;
    }

    /**
     * 닉네임으로 회원이 존재하는지 확인하기
     * @param nickname 닉네임
     * @return 회원이 존재하면 true, 존재하지 않으면 false
     */
    private boolean existsByNickname(String nickname) {
        return false;
    }

    /**
     * 이메일로 회원이 존재하는지 확인하기
     * @param email 이메일
     * @return 회원이 존재하면 true, 존재하지 않으면 false
     */
    private boolean existsByEmail(String email) {
        return false;
    }

    /**
     * 비밀번호 암호화하기
     * @param password 비밀번호
     * @return 암호화된 비밀번호
     */
    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
