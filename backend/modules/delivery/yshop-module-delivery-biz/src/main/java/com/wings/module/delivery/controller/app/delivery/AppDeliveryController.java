package com.wings.module.delivery.controller.app.delivery;

import com.wings.framework.common.pojo.CommonResult;
import com.wings.module.delivery.controller.app.delivery.vo.AppOrderDeliveryRespVO;
import com.wings.module.delivery.service.DeliveryTaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.wings.framework.common.pojo.CommonResult.success;

@Tag(name = "App - Delivery")
@RestController
@RequestMapping({"/order", "/orders"})
public class AppDeliveryController {

    @Resource
    private DeliveryTaskService deliveryTaskService;

    @GetMapping("/{orderId}/delivery")
    @Operation(summary = "Get order delivery info")
    public CommonResult<AppOrderDeliveryRespVO> getOrderDelivery(@PathVariable("orderId") Long orderId) {
        return success(deliveryTaskService.getOrderDelivery(orderId).orElse(null));
    }
}
