package com.ordering.module.product.service.storeproductattrresult;

import com.ordering.module.product.dal.dataobject.storeproductattrresult.StoreProductAttrResultDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商品属性详情 Service 接口
 *
 * @author project team
 */
public interface StoreProductAttrResultService extends IService<StoreProductAttrResultDO> {

    /**
     * 删除商品属性详情
     *
     * @param id 编号
     */
    void deleteStoreProductAttrResult(Long id);

    /**
     * 获得商品属性详情
     *
     * @param id 编号
     * @return 商品属性详情
     */
    StoreProductAttrResultDO getStoreProductAttrResult(Long id);

    /**
     * 获得商品属性详情列表
     *
     * @param ids 编号
     * @return 商品属性详情列表
     */
    List<StoreProductAttrResultDO> getStoreProductAttrResultList(Collection<Long> ids);


    /**
     * 新增商品属性详情
     * @param map map
     * @param productId 商品id
     */
    void insertLegacyStoreProductAttrResult(Map<String, Object> map, Long productId);

}
