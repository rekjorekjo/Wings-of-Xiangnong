package com.wings.module.order.convert.storeordercartinfo;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 订单购物详情 Convert
 *
 * @author wings
 */
@Mapper
public interface StoreOrderCartInfoConvert {

    StoreOrderCartInfoConvert INSTANCE = Mappers.getMapper(StoreOrderCartInfoConvert.class);

}
