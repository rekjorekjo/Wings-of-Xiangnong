package com.wings.module.product.convert.storeproductattr;

import com.wings.module.product.controller.admin.storeproduct.vo.StoreProductRespVO;
import com.wings.module.product.controller.app.product.vo.AppStoreProductAttrQueryVo;
import com.wings.module.product.dal.dataobject.storeproduct.StoreProductDO;
import com.wings.module.product.dal.dataobject.storeproductattr.StoreProductAttrDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 商品属性 Convert
 *
 * @author wings
 */
@Mapper
public interface StoreProductAttrConvert {

    StoreProductAttrConvert INSTANCE = Mappers.getMapper(StoreProductAttrConvert.class);

    AppStoreProductAttrQueryVo convert(StoreProductAttrDO bean);
    

}
