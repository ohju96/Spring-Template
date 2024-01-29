package com.oh.template.app.member.infra;

import com.oh.template.app.member.command.application.MemberService;
import com.oh.template.app.member.command.application.request.SignupRequest;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Override
    public void signup(SignupRequest signupRequest) {

    }
}
