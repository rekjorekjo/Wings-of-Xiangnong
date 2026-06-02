package com.ordering.member.controller.admin.userbill;

import com.ordering.framework.common.pojo.CommonResult;
import com.ordering.framework.common.pojo.PageResult;
import com.ordering.member.controller.admin.userbill.vo.UserBillPageReqVO;
import com.ordering.member.controller.admin.userbill.vo.UserBillRespVO;
import com.ordering.member.convert.userbill.UserBillConvert;
import com.ordering.member.dal.dataobject.userbill.UserBillDO;
import com.ordering.member.service.userbill.UserBillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static com.ordering.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 用户账单")
@RestController
@RequestMapping("/member/user-bill")
@Validated
public class UserBillController {

    @Resource
    private UserBillService userBillService;


    @GetMapping("/page")
    @Operation(summary = "获得用户账单分页")
    @PreAuthorize("@ss.hasPermission('member:user-bill:query')")
    public CommonResult<PageResult<UserBillRespVO>> getUserBillPage(@Valid UserBillPageReqVO pageVO) {
        PageResult<UserBillDO> pageResult = userBillService.getUserBillPage(pageVO);
        return success(UserBillConvert.INSTANCE.convertPage(pageResult));
    }







}
