package co.yixiang.yshop.module.delivery.controller.admin.delivery.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 配送任务 Response VO")
public record AdminDeliveryTaskRespVO(
        @Schema(description = "任务ID", example = "10001")
        Long id,
        @Schema(description = "订单编号", example = "XN202605230001")
        String orderNo,
        @Schema(description = "取货站点", example = "Xiangnong Station A")
        String pickupSite,
        @Schema(description = "送达站点", example = "Mingde Building pickup point")
        String dropoffSite,
        @Schema(description = "无人机编号", example = "XN-D002")
        String droneNo,
        @Schema(description = "状态", example = "flying")
        String status,
        @Schema(description = "状态文本", example = "配送中")
        String statusText,
        @Schema(description = "配送进度百分比", example = "65")
        Integer progress,
        @Schema(description = "预计到达分钟数", example = "9")
        Integer etaMinutes,
        @Schema(description = "创建时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt,
        @Schema(description = "任务时间线")
        List<TimelineVO> timeline
) {
    @Schema(description = "任务时间线 VO")
    public record TimelineVO(
            @Schema(description = "状态", example = "assigned")
            String status,
            @Schema(description = "标题", example = "无人机已分配")
            String title,
            @Schema(description = "时间")
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            LocalDateTime time
    ) {}
}
