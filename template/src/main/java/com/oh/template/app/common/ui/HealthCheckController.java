package com.oh.template.app.common.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Health", description = "서버 상태 체크")
@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController {

    @Operation(summary = "서버 상태 체크")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping("")
    public String healthCheck() {
            return "OK";
        }
}
