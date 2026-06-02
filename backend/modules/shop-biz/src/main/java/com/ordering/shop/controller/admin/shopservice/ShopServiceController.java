package com.ordering.shop.controller.admin.shopservice;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import java.util.*;
import java.io.IOException;

import com.ordering.framework.common.pojo.PageResult;
import com.ordering.framework.common.pojo.CommonResult;
import static com.ordering.framework.common.pojo.CommonResult.success;

import com.ordering.framework.excel.core.util.ExcelUtils;

import com.ordering.shop.controller.admin.shopservice.vo.*;
import com.ordering.shop.dal.dataobject.shopservice.ShopServiceDO;
import com.ordering.shop.convert.shopservice.ShopServiceConvert;
import com.ordering.shop.service.shopservice.ShopService;

@Tag(name = "管理后台 - 我的服务")
@RestController
@RequestMapping("/shop/service")
@Validated
public class ShopServiceController {

    @Resource
    private ShopService shopService;

    @PostMapping("/create")
    @Operation(summary = "创建我的服务")
    @PreAuthorize("@ss.hasPermission('shop:service:create')")
    public CommonResult<Long> createService(@Valid @RequestBody ShopServiceCreateReqVO createReqVO) {
        return success(shopService.createService(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新我的服务")
    @PreAuthorize("@ss.hasPermission('shop:service:update')")
    public CommonResult<Boolean> updateService(@Valid @RequestBody ShopServiceUpdateReqVO updateReqVO) {
        shopService.updateService(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除我的服务")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('shop:service:delete')")
    public CommonResult<Boolean> deleteService(@RequestParam("id") Long id) {
        shopService.deleteService(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得我的服务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('shop:service:query')")
    public CommonResult<ShopServiceRespVO> getService(@RequestParam("id") Long id) {
        ShopServiceDO service = shopService.getService(id);
        return success(ShopServiceConvert.INSTANCE.convert(service));
    }

    @GetMapping("/list")
    @Operation(summary = "获得我的服务列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('shop:service:query')")
    public CommonResult<List<ShopServiceRespVO>> getServiceList(@RequestParam("ids") Collection<Long> ids) {
        List<ShopServiceDO> list = shopService.getServiceList(ids);
        return success(ShopServiceConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得我的服务分页")
    @PreAuthorize("@ss.hasPermission('shop:service:query')")
    public CommonResult<PageResult<ShopServiceRespVO>> getServicePage(@Valid ShopServicePageReqVO pageVO) {
        PageResult<ShopServiceDO> pageResult = shopService.getServicePage(pageVO);
        return success(ShopServiceConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出我的服务 Excel")
    @PreAuthorize("@ss.hasPermission('shop:service:export')")
    public void exportServiceExcel(@Valid ShopServiceExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ShopServiceDO> list = shopService.getServiceList(exportReqVO);
        // 导出 Excel
        List<ShopServiceExcelVO> datas = ShopServiceConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "我的服务.xls", "数据", ShopServiceExcelVO.class, datas);
    }

}
