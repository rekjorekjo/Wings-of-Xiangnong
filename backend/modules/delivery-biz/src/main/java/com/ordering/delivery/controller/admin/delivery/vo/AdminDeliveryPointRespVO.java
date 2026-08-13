package com.ordering.delivery.controller.admin.delivery.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 无人机配送点 Response VO")
public record AdminDeliveryPointRespVO(
        @Schema(description = "配送点ID", example = "1")
        Long id,
        @Schema(description = "配送点编码", example = "T_LIBRARY")
        String code,
        @Schema(description = "配送点名称", example = "图书馆草坪降落点")
        String name,
        @Schema(description = "地址描述", example = "东南大学九龙湖校区图书馆草坪")
        String address,
        @Schema(description = "纬度，WGS-84", example = "31.88791480")
        BigDecimal latitude,
        @Schema(description = "经度，WGS-84", example = "118.81327290")
        BigDecimal longitude,
        @Schema(description = "默认巡航高度，米", example = "10.0")
        BigDecimal flightAltitude,
        @Schema(description = "是否启用")
        Boolean enabled,
        @Schema(description = "是否已由无人机组验证")
        Boolean verified,
        @Schema(description = "排序", example = "10")
        Integer sort,
        @Schema(description = "备注/安全说明")
        String remark,
        @Schema(description = "距用户坐标的距离，米；未传用户坐标时为空", example = "18.4")
        Double distanceMeters,
        @Schema(description = "是否推荐点")
        Boolean recommended
) {
}
