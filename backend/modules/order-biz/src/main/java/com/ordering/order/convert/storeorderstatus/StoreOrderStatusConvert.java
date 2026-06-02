package com.ordering.order.convert.storeorderstatus;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 订单操作记录 Convert
 *
 * @author project team
 */
@Mapper
public interface StoreOrderStatusConvert {

    StoreOrderStatusConvert INSTANCE = Mappers.getMapper(StoreOrderStatusConvert.class);


}
