package co.yixiang.yshop.module.delivery.controller.admin.delivery;

import co.yixiang.yshop.framework.common.pojo.CommonResult;
import co.yixiang.yshop.module.delivery.service.DeliveryMockService;
import co.yixiang.yshop.module.delivery.service.DeliveryMockService.DeliveryDroneRespVO;
import co.yixiang.yshop.module.delivery.service.DeliveryMockService.DeliveryTaskRespVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static co.yixiang.yshop.framework.common.pojo.CommonResult.success;

@Tag(name = "Admin - Delivery mock")
@RestController
@RequestMapping("/delivery")
@Validated
public class DeliveryAdminController {

    @Resource
    private DeliveryMockService deliveryMockService;

    @GetMapping("/tasks")
    @Operation(summary = "Get delivery mock tasks")
    public CommonResult<List<DeliveryTaskRespVO>> getDeliveryTasks(
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "droneNo", required = false) String droneNo) {
        return success(deliveryMockService.getTasks(status, droneNo));
    }

    @GetMapping("/drones")
    @Operation(summary = "Get delivery mock drones")
    public CommonResult<List<DeliveryDroneRespVO>> getDeliveryDrones() {
        return success(deliveryMockService.getDrones());
    }
}
