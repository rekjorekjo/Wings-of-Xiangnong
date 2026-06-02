package com.ordering.module.delivery.controller.admin.delivery;

import com.ordering.framework.common.pojo.CommonResult;
import com.ordering.module.delivery.controller.admin.delivery.vo.AdminDeliveryDroneRespVO;
import com.ordering.module.delivery.controller.admin.delivery.vo.AdminDeliveryTaskRespVO;
import com.ordering.module.delivery.service.DeliveryDroneService;
import com.ordering.module.delivery.service.DeliveryTaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ordering.framework.common.pojo.CommonResult.success;

@Tag(name = "Admin - Delivery")
@RestController
@RequestMapping("/delivery")
@Validated
public class DeliveryAdminController {

    @Resource
    private DeliveryTaskService deliveryTaskService;

    @Resource
    private DeliveryDroneService deliveryDroneService;

    @GetMapping("/tasks")
    @Operation(summary = "Get delivery tasks")
    public CommonResult<List<AdminDeliveryTaskRespVO>> getDeliveryTasks(
            @RequestParam(value = "orderNo", required = false) String orderNo,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "droneNo", required = false) String droneNo) {
        return success(deliveryTaskService.getTaskList(orderNo, status, droneNo));
    }

    @GetMapping("/drones")
    @Operation(summary = "Get delivery drones")
    public CommonResult<List<AdminDeliveryDroneRespVO>> getDeliveryDrones() {
        return success(deliveryDroneService.getDroneList());
    }
}
