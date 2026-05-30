package co.yixiang.yshop.module.delivery.controller.app.delivery.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "小程序 - 订单配送信息 Response VO")
public record AppOrderDeliveryRespVO(
        @Schema(description = "订单ID", example = "30001")
        Long orderId,
        @Schema(description = "配送任务ID", example = "10001")
        Long deliveryTaskId,
        @Schema(description = "状态", example = "flying")
        String status,
        @Schema(description = "状态文本", example = "配送中")
        String statusText,
        @Schema(description = "预计到达时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime estimatedArrivalTime,
        @Schema(description = "当前位置", example = "飞越图书馆上空")
        String currentLocation,
        @Schema(description = "配送进度")
        List<ProgressVO> progress
) {
    @Schema(description = "配送进度 VO")
    public record ProgressVO(
            @Schema(description = "状态", example = "assigned")
            String status,
            @Schema(description = "标题", example = "无人机已分配")
            String title,
            @Schema(description = "时间")
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            LocalDateTime time
    ) {}
}
