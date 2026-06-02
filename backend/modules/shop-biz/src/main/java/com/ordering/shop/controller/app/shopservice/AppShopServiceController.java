/**
 * Copyright (C) 2018-2022
 * 注意：
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package com.ordering.shop.controller.app.shopservice;


import com.ordering.framework.common.enums.ShopCommonEnum;
import com.ordering.framework.common.pojo.CommonResult;
import com.ordering.shop.controller.app.shopservice.vo.AppShopServiceVO;
import com.ordering.shop.convert.shopservice.ShopServiceConvert;
import com.ordering.shop.dal.dataobject.shopservice.ShopServiceDO;
import com.ordering.shop.service.shopservice.AppShopService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ordering.framework.common.pojo.CommonResult.success;

/**
 * <p>
 * 首页广告控制器
 * </p>
 *
 * @author hupeng
 * @since 2023-8-11
 */
@Slf4j
@RestController
@Tag(name = "用户 APP - 服务菜单")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/service")
public class AppShopServiceController {

    private final AppShopService appShopService;


    @GetMapping("/list")
    @Operation(summary = "服务菜单列表")
    public CommonResult<List<AppShopServiceVO>> getList() {
        List<ShopServiceDO> appShopAdsVOS = appShopService.list(new LambdaQueryWrapper<ShopServiceDO>()
                .eq(ShopServiceDO::getStatus, ShopCommonEnum.IS_STATUS_1.getValue()).orderByDesc(ShopServiceDO::getWeigh));
        return success(ShopServiceConvert.INSTANCE.convertList03(appShopAdsVOS));
    }

    @GetMapping("/content")
    @Operation(summary = "服务菜单列表")
    public CommonResult<AppShopServiceVO> getContent(@RequestParam("id") Integer id) {
        ShopServiceDO appShopAdsVO = appShopService.getById(id);
        return success(ShopServiceConvert.INSTANCE.convert03(appShopAdsVO));
    }



}

