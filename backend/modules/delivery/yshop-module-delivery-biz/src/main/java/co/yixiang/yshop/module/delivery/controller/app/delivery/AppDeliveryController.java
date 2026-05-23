package co.yixiang.yshop.module.delivery.controller.app.delivery;

import co.yixiang.yshop.framework.common.pojo.CommonResult;
import co.yixiang.yshop.module.delivery.controller.app.delivery.vo.AppOrderDeliveryRespVO;
import co.yixiang.yshop.module.delivery.service.DeliveryMockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static co.yixiang.yshop.framework.common.pojo.CommonResult.success;

@Tag(name = "App - Delivery mock")
@RestController
@RequestMapping("/order")
public class AppDeliveryController {

    @Resource
    private DeliveryMockService deliveryMockService;

    @GetMapping("/{orderId}/delivery")
    @Operation(summary = "Get order delivery mock info")
    public CommonResult<AppOrderDeliveryRespVO> getOrderDelivery(@PathVariable("orderId") Long orderId) {
        return success(deliveryMockService.getOrderDelivery(orderId).orElse(null));
    }
}
