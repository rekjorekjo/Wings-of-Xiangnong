package com.wings.module.order.convert.storeorderstatus;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 订单操作记录 Convert
 *
 * @author wings
 */
@Mapper
public interface StoreOrderStatusConvert {

    StoreOrderStatusConvert INSTANCE = Mappers.getMapper(StoreOrderStatusConvert.class);


}
