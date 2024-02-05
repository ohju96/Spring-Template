package com.oh.template.app.member.infra;

import com.oh.template.app.common.exception.CustomException;
import com.oh.template.app.member.command.application.MemberService;
import com.oh.template.app.member.command.application.request.KakaoSignupRequest;
import com.oh.template.app.member.command.application.request.SignupRequest;
import com.oh.template.app.member.command.domain.Member;
import com.oh.template.app.member.command.domain.Role;
import com.oh.template.app.member.query.dto.MemberDto;
import com.oh.template.app.member.query.response.KakaoSignupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

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

        // 회원가입 가능한지 확인하기
        checkIfSignupAllowed(memberDto);

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
    private KakaoSignupResponse signupKakao(MemberDto memberDto) {

        // 스크랩 요청 객체 생성
        KakaoSignupRequest kakaoSignupRequest = KakaoSignupRequest.of(
                memberDto.email(),
                memberDto.providerId(),
                memberDto.nickname(),
                memberDto.name(),
                memberDto.password()
        );

        // Base URL
        String reqBaseUrl = "https://kauth.kakao.com";

        // 웹 클라이언트 생성
        WebClient client = WebClient.builder()
                .baseUrl(reqBaseUrl)
                .build();

        // URI
        String reqUri = "/oauth/authorize";

        ResponseEntity<KakaoSignupResponse> result = client.post()
                .uri(reqUri)
                .bodyValue(kakaoSignupRequest)
                .retrieve()
                .toEntity(KakaoSignupResponse.class)
                .block();

        // 실패시 예외 발생
        if (result == null || result.getStatusCode().isError()) {
            throw new CustomException(FAILED_TO_WEBCLIENT_REQUEST);
        }

        return result.getBody();
    }

    /**
     * 일반(이메일) 회원가입하기
     * @param memberDto 멤버 DTO
     */
    private void signupNone(MemberDto memberDto) {

    }

    /**
     * 회원가입 가능한지 확인하기
     * @param memberDto 멤버 DTO
     */
    private void checkIfSignupAllowed(MemberDto memberDto) {

        Member member = MemberDto.toEntity(memberDto);

        // 이미 존재하는 회원인지 확인하기
        if (memberRepository.existsByProviderId(member)) {
            throw new CustomException(ALREADY_EXIST_MEMBER);
        }

        // 이미 존재하는 닉네임인지 확인하기
        if (memberRepository.existsByNickname(member)) {
            throw new CustomException(ALREADY_EXIST_NICKNAME);
        }

        // 이미 존재하는 이메일인지 확인하기
        if (memberRepository.existsByEmail(member)) {
            throw new CustomException(ALREADY_EXIST_EMAIL);
        }

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
