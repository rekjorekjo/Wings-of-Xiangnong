package com.wings.module.delivery.controller.admin.delivery.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 无人机 Response VO")
public record AdminDeliveryDroneRespVO(
        @Schema(description = "无人机ID", example = "1")
        Long id,
        @Schema(description = "无人机编号", example = "XN-D001")
        String droneNo,
        @Schema(description = "状态", example = "idle")
        String status,
        @Schema(description = "状态文本", example = "空闲")
        String statusText,
        @Schema(description = "电量百分比", example = "92")
        Integer battery,
        @Schema(description = "当前位置", example = "Station A")
        String location,
        @Schema(description = "载重信息", example = "2杯咖啡")
        String payload,
        @Schema(description = "最后更新时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime lastUpdatedAt
) {}
