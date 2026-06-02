package com.ordering.product.dal.dataobject.storeproductattr;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.ordering.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商品属性 DO
 *
 * @author project team
 */
@TableName("app_store_product_attr")
@KeySequence("app_store_product_attr_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreProductAttrDO{

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 属性名
     */
    private String attrName;
    /**
     * 属性值
     */
    private String attrValues;

}
