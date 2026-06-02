package com.ordering.module.shop.convert.shopads;

import java.util.*;

import com.ordering.framework.common.pojo.PageResult;

import com.ordering.module.shop.controller.app.ad.vo.AppShopAdsVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ordering.module.shop.controller.admin.shopads.vo.*;
import com.ordering.module.shop.dal.dataobject.shopads.ShopAdsDO;

/**
 * 广告图管理 Convert
 *
 * @author project team
 */
@Mapper
public interface ShopAdsConvert {

    ShopAdsConvert INSTANCE = Mappers.getMapper(ShopAdsConvert.class);

    ShopAdsDO convert(ShopAdsCreateReqVO bean);

    ShopAdsDO convert(ShopAdsUpdateReqVO bean);

    ShopAdsRespVO convert(ShopAdsDO bean);

    List<ShopAdsRespVO> convertList(List<ShopAdsDO> list);

    List<AppShopAdsVO> convertList03(List<ShopAdsDO> list);

    PageResult<ShopAdsRespVO> convertPage(PageResult<ShopAdsDO> page);

    List<ShopAdsExcelVO> convertList02(List<ShopAdsDO> list);

}
