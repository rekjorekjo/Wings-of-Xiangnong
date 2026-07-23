package com.ordering.member.controller.app.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "用户 APP - 本地测试登录 Request VO（仅开发环境可用）")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppAuthMockLoginReqVO {

    @Schema(description = "手机号，不传则默认 13800000000", example = "13800000000")
    private String mobile;

}
