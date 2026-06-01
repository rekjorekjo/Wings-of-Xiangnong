package com.wings.module.shop.dal.mysql.shopads;

import java.util.*;

import com.wings.framework.common.pojo.PageResult;
import com.wings.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.wings.framework.mybatis.core.mapper.BaseMapperX;
import com.wings.module.shop.dal.dataobject.shopads.ShopAdsDO;
import org.apache.ibatis.annotations.Mapper;
import com.wings.module.shop.controller.admin.shopads.vo.*;

/**
 * 广告图管理 Mapper
 *
 * @author wings
 */
@Mapper
public interface ShopAdsMapper extends BaseMapperX<ShopAdsDO> {

    default PageResult<ShopAdsDO> selectPage(ShopAdsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ShopAdsDO>()
                .eqIfPresent(ShopAdsDO::getImage, reqVO.getImage())
                .eqIfPresent(ShopAdsDO::getIsSwitch, reqVO.getIsSwitch())
                .eqIfPresent(ShopAdsDO::getWeigh, reqVO.getWeigh())
                .likeIfPresent(ShopAdsDO::getShopName, reqVO.getShopName())
                .betweenIfPresent(ShopAdsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ShopAdsDO::getId));
    }

    default List<ShopAdsDO> selectList(ShopAdsExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ShopAdsDO>()
                .eqIfPresent(ShopAdsDO::getImage, reqVO.getImage())
                .eqIfPresent(ShopAdsDO::getIsSwitch, reqVO.getIsSwitch())
                .eqIfPresent(ShopAdsDO::getWeigh, reqVO.getWeigh())
                .eqIfPresent(ShopAdsDO::getShopId, reqVO.getShopId())
                .betweenIfPresent(ShopAdsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ShopAdsDO::getId));
    }

}
