package com.ordering.system.controller.admin.social;

import com.ordering.framework.common.enums.UserTypeEnum;
import com.ordering.framework.common.pojo.CommonResult;
import com.ordering.framework.common.pojo.PageResult;
import com.ordering.framework.common.util.object.BeanUtils;
import com.ordering.system.controller.admin.social.vo.user.SocialUserBindReqVO;
import com.ordering.system.controller.admin.social.vo.user.SocialUserUnbindReqVO;
import com.ordering.system.controller.admin.social.vo.user.SocialUserPageReqVO;
import com.ordering.system.controller.admin.social.vo.user.SocialUserRespVO;
import com.ordering.system.convert.social.SocialUserConvert;
import com.ordering.system.dal.dataobject.social.SocialUserDO;
import com.ordering.system.service.social.SocialUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

import static com.ordering.framework.common.pojo.CommonResult.success;
import static com.ordering.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "管理后台 - 社交用户")
@RestController
@RequestMapping("/system/social-user")
@Validated
public class SocialUserController {

    @Resource
    private SocialUserService socialUserService;

    @PostMapping("/bind")
    @Operation(summary = "社交绑定，使用 code 授权码")
    public CommonResult<Boolean> socialBind(@RequestBody @Valid SocialUserBindReqVO reqVO) {
        socialUserService.bindSocialUser(SocialUserConvert.INSTANCE.convert(
                getLoginUserId(), UserTypeEnum.ADMIN.getValue(), reqVO));
        return CommonResult.success(true);
    }

    @DeleteMapping("/unbind")
    @Operation(summary = "取消社交绑定")
    public CommonResult<Boolean> socialUnbind(@RequestBody SocialUserUnbindReqVO reqVO) {
        socialUserService.unbindSocialUser(getLoginUserId(), UserTypeEnum.ADMIN.getValue(), reqVO.getType(), reqVO.getOpenid());
        return CommonResult.success(true);
    }

    // ==================== 社交用户 CRUD ====================

    @GetMapping("/get")
    @Operation(summary = "获得社交用户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:social-user:query')")
    public CommonResult<SocialUserRespVO> getSocialUser(@RequestParam("id") Long id) {
        SocialUserDO socialUser = socialUserService.getSocialUser(id);
        return success(BeanUtils.toBean(socialUser, SocialUserRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得社交用户分页")
    @PreAuthorize("@ss.hasPermission('system:social-user:query')")
    public CommonResult<PageResult<SocialUserRespVO>> getSocialUserPage(@Valid SocialUserPageReqVO pageVO) {
        PageResult<SocialUserDO> pageResult = socialUserService.getSocialUserPage(pageVO);
        return success(BeanUtils.toBean(pageResult, SocialUserRespVO.class));
    }

}
