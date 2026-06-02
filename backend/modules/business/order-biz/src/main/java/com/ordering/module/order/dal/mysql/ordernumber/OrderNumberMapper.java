package com.ordering.module.order.dal.mysql.ordernumber;

import com.ordering.framework.mybatis.core.mapper.BaseMapperX;
import com.ordering.module.order.dal.dataobject.ordernumber.OrderNumberDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单 Mapper
 *
 * @author project team
 */
@Mapper
public interface OrderNumberMapper extends BaseMapperX<OrderNumberDO> {

}
