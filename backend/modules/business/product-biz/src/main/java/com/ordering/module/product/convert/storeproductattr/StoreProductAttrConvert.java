package com.ordering.module.product.convert.storeproductattr;

import com.ordering.module.product.controller.admin.storeproduct.vo.StoreProductRespVO;
import com.ordering.module.product.controller.app.product.vo.AppStoreProductAttrQueryVo;
import com.ordering.module.product.dal.dataobject.storeproduct.StoreProductDO;
import com.ordering.module.product.dal.dataobject.storeproductattr.StoreProductAttrDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 商品属性 Convert
 *
 * @author project team
 */
@Mapper
public interface StoreProductAttrConvert {

    StoreProductAttrConvert INSTANCE = Mappers.getMapper(StoreProductAttrConvert.class);

    AppStoreProductAttrQueryVo convert(StoreProductAttrDO bean);
    

}
