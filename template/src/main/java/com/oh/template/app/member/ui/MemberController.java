package com.oh.template.app.member.ui;

import com.oh.template.app.common.query.response.CustomResponseUtils;
import com.oh.template.app.common.ui.ControllerSupport;
import com.oh.template.app.member.command.application.MemberService;
import com.oh.template.app.member.command.application.request.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.oh.template.app.common.query.response.CustomResponseUtils.CustomResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController extends ControllerSupport {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<CustomResponse<Void>> signup(
            @RequestBody SignupRequest request
    ) {
        memberService.signup(request);
        return ResponseEntity.ok().body(
                CustomResponseUtils.success(null)
        );
    }
}
