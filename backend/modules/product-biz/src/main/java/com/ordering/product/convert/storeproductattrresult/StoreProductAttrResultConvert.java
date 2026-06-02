package com.ordering.product.convert.storeproductattrresult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 商品属性详情 Convert
 *
 * @author project team
 */
@Mapper
public interface StoreProductAttrResultConvert {

    StoreProductAttrResultConvert INSTANCE = Mappers.getMapper(StoreProductAttrResultConvert.class);

}
