package com.wings.module.product.service.storeproductattr;

import com.wings.module.product.dal.dataobject.storeproductattr.StoreProductAttrDO;
import com.wings.module.product.service.storeproduct.dto.FromatDetailDto;
import com.wings.module.product.service.storeproduct.dto.ProductFormatDto;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * 商品属性 Service 接口
 *
 * @author wings
 */
public interface StoreProductAttrService extends IService<StoreProductAttrDO>  {

    /**
     * 删除商品属性
     *
     * @param id 编号
     */
    void deleteStoreProductAttr(Long id);

    /**
     * 获得商品属性
     *
     * @param id 编号
     * @return 商品属性
     */
    StoreProductAttrDO getStoreProductAttr(Long id);

    /**
     * 获得商品属性列表
     *
     * @param ids 编号
     * @return 商品属性列表
     */
    List<StoreProductAttrDO> getStoreProductAttrList(Collection<Long> ids);


    /**
     * 新增商品属性
     * @param items attr
     * @param attrs value
     * @param productId 商品id
     */
    void insertYxStoreProductAttr(List<FromatDetailDto> items, List<ProductFormatDto> attrs,
                                  Long productId);

}
