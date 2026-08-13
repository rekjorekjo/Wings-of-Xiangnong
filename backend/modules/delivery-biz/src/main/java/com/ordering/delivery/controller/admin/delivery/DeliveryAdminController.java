package com.ordering.delivery.controller.admin.delivery;

import com.ordering.framework.common.pojo.CommonResult;
import com.ordering.delivery.controller.admin.delivery.vo.AdminDeliveryDroneRespVO;
import com.ordering.delivery.controller.admin.delivery.vo.AdminDeliveryPointRespVO;
import com.ordering.delivery.controller.admin.delivery.vo.AdminDeliveryPointSaveReqVO;
import com.ordering.delivery.controller.admin.delivery.vo.AdminDeliveryTaskRespVO;
import com.ordering.delivery.service.DeliveryPointService;
import com.ordering.delivery.service.DeliveryDroneService;
import com.ordering.delivery.service.DeliveryTaskService;
import com.ordering.delivery.service.WaypointMissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
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

    @Resource
    private DeliveryPointService deliveryPointService;

    @Resource
    private WaypointMissionService waypointMissionService;

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

    @GetMapping("/points")
    @Operation(summary = "Get delivery points")
    public CommonResult<List<AdminDeliveryPointRespVO>> getDeliveryPoints(
            @RequestParam(value = "userLatitude", required = false) BigDecimal userLatitude,
            @RequestParam(value = "userLongitude", required = false) BigDecimal userLongitude) {
        return success(deliveryPointService.listPoints(userLatitude, userLongitude));
    }

    @GetMapping("/points/recommend")
    @Operation(summary = "Recommend nearest delivery point")
    public CommonResult<AdminDeliveryPointRespVO> recommendDeliveryPoint(
            @RequestParam("userLatitude") BigDecimal userLatitude,
            @RequestParam("userLongitude") BigDecimal userLongitude) {
        return success(deliveryPointService.recommendPoint(userLatitude, userLongitude));
    }

    @PostMapping("/points")
    @Operation(summary = "Create delivery point")
    public CommonResult<Long> createDeliveryPoint(@Valid @RequestBody AdminDeliveryPointSaveReqVO reqVO) {
        return success(deliveryPointService.createPoint(reqVO));
    }

    @PutMapping("/points/{id}")
    @Operation(summary = "Update delivery point")
    public CommonResult<Boolean> updateDeliveryPoint(
            @PathVariable("id") Long id,
            @Valid @RequestBody AdminDeliveryPointSaveReqVO reqVO) {
        return success(deliveryPointService.updatePoint(id, reqVO));
    }

    @DeleteMapping("/points/{id}")
    @Operation(summary = "Delete delivery point")
    public CommonResult<Boolean> deleteDeliveryPoint(@PathVariable("id") Long id) {
        return success(deliveryPointService.deletePoint(id));
    }

    @GetMapping("/tasks/{id}/waypoints")
    @Operation(summary = "Download waypoint mission file")
    public ResponseEntity<ByteArrayResource> downloadWaypointMission(
            @PathVariable("id") Long id,
            @RequestParam("destinationLatitude") BigDecimal destinationLatitude,
            @RequestParam("destinationLongitude") BigDecimal destinationLongitude,
            @RequestParam(value = "startLatitude", required = false) BigDecimal startLatitude,
            @RequestParam(value = "startLongitude", required = false) BigDecimal startLongitude,
            @RequestParam(value = "homeAltitude", required = false) BigDecimal homeAltitude,
            @RequestParam(value = "flightAltitude", required = false) BigDecimal flightAltitude) {
        WaypointMissionService.WaypointMissionFile file = waypointMissionService.generateMission(
                id,
                destinationLatitude,
                destinationLongitude,
                startLatitude,
                startLongitude,
                homeAltitude,
                flightAltitude);
        ByteArrayResource resource = new ByteArrayResource(file.content());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(file.content().length)
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                        .filename(file.fileName())
                .build()
                .toString())
                .body(resource);
    }

    @GetMapping("/tasks/{id}/waypoints/preview")
    @Operation(summary = "Preview waypoint mission file")
    public CommonResult<String> previewWaypointMission(
            @PathVariable("id") Long id,
            @RequestParam("destinationLatitude") BigDecimal destinationLatitude,
            @RequestParam("destinationLongitude") BigDecimal destinationLongitude,
            @RequestParam(value = "startLatitude", required = false) BigDecimal startLatitude,
            @RequestParam(value = "startLongitude", required = false) BigDecimal startLongitude,
            @RequestParam(value = "homeAltitude", required = false) BigDecimal homeAltitude,
            @RequestParam(value = "flightAltitude", required = false) BigDecimal flightAltitude) {
        WaypointMissionService.WaypointMissionFile file = waypointMissionService.generateMission(
                id,
                destinationLatitude,
                destinationLongitude,
                startLatitude,
                startLongitude,
                homeAltitude,
                flightAltitude);
        return success(file.asText());
    }
}
