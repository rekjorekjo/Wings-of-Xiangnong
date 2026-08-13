package com.ordering.delivery.controller.admin.delivery.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "管理后台 - 无人机配送点保存 Request VO")
public class AdminDeliveryPointSaveReqVO {

    @NotBlank(message = "配送点编码不能为空")
    @Schema(description = "配送点编码", example = "T_LIBRARY")
    private String code;

    @NotBlank(message = "配送点名称不能为空")
    @Schema(description = "配送点名称", example = "图书馆草坪降落点")
    private String name;

    @Schema(description = "地址描述", example = "东南大学九龙湖校区图书馆草坪")
    private String address;

    @NotNull(message = "纬度不能为空")
    @DecimalMin(value = "-90", message = "纬度不能小于 -90")
    @DecimalMax(value = "90", message = "纬度不能大于 90")
    @Schema(description = "纬度，WGS-84", example = "31.88791480")
    private BigDecimal latitude;

    @NotNull(message = "经度不能为空")
    @DecimalMin(value = "-180", message = "经度不能小于 -180")
    @DecimalMax(value = "180", message = "经度不能大于 180")
    @Schema(description = "经度，WGS-84", example = "118.81327290")
    private BigDecimal longitude;

    @NotNull(message = "巡航高度不能为空")
    @DecimalMin(value = "1", message = "巡航高度不能小于 1 米")
    @Schema(description = "默认巡航高度，米", example = "10.0")
    private BigDecimal flightAltitude;

    @Schema(description = "是否启用")
    private Boolean enabled = true;

    @Schema(description = "是否已由无人机组验证")
    private Boolean verified = false;

    @Schema(description = "排序", example = "10")
    private Integer sort = 0;

    @Schema(description = "备注/安全说明")
    private String remark;
}
