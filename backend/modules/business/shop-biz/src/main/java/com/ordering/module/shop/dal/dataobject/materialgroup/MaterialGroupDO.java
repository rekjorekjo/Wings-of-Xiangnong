package com.ordering.module.shop.dal.dataobject.materialgroup;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ordering.framework.mybatis.core.dataobject.BaseDO;

/**
 * 素材分组 DO
 *
 * @author project team
 */
@TableName("app_material_group")
@KeySequence("app_material_group_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialGroupDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 分组名
     */
    private String name;

}
