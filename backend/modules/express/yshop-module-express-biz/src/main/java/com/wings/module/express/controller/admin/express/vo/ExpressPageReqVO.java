package com.wings.module.express.controller.admin.express.vo;

import com.wings.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 快递公司分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExpressPageReqVO extends PageParam {

    @Schema(description = "快递公司简称")
    private String code;

    @Schema(description = "快递公司全称", example = "wings")
    private String name;

}
