package com.ordering.module.shop.dal.dataobject.shopads;

import com.ordering.framework.mybatis.core.type.StringListTypeHandler;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ordering.framework.mybatis.core.dataobject.BaseDO;

/**
 * 广告图管理 DO
 *
 * @author project team
 */
@TableName(value = "app_shop_ads")
@KeySequence("app_shop_ads_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopAdsDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 图片
     */
    private String image;
    /**
     * 是否显现
     */
    private Integer isSwitch;
    /**
     * 权重
     */
    private Integer weigh;
    /**
     * 所支持的店铺id用','区分，0代表全部
     */
    private String shopId;

    private String shopName;

}
