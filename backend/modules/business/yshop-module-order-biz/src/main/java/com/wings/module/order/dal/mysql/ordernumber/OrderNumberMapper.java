package com.wings.module.order.dal.mysql.ordernumber;

import com.wings.framework.mybatis.core.mapper.BaseMapperX;
import com.wings.module.order.dal.dataobject.ordernumber.OrderNumberDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单 Mapper
 *
 * @author wings
 */
@Mapper
public interface OrderNumberMapper extends BaseMapperX<OrderNumberDO> {

}
