package com.oh.template.app.member.command.application;

import com.oh.template.app.member.command.application.request.SignupRequest;

public interface MemberService {
    void signup(SignupRequest signupRequest);
}
