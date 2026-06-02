package com.ordering.shop.convert.shopservice;

import java.util.*;

import com.ordering.framework.common.pojo.PageResult;

import com.ordering.shop.controller.app.shopservice.vo.AppShopServiceVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ordering.shop.controller.admin.shopservice.vo.*;
import com.ordering.shop.dal.dataobject.shopservice.ShopServiceDO;

/**
 * 我的服务 Convert
 *
 * @author project team
 */
@Mapper
public interface ShopServiceConvert {

    ShopServiceConvert INSTANCE = Mappers.getMapper(ShopServiceConvert.class);

    ShopServiceDO convert(ShopServiceCreateReqVO bean);

    ShopServiceDO convert(ShopServiceUpdateReqVO bean);

    ShopServiceRespVO convert(ShopServiceDO bean);

    List<ShopServiceRespVO> convertList(List<ShopServiceDO> list);

    List<AppShopServiceVO> convertList03(List<ShopServiceDO> list);

    AppShopServiceVO convert03(ShopServiceDO bean);

    PageResult<ShopServiceRespVO> convertPage(PageResult<ShopServiceDO> page);

    List<ShopServiceExcelVO> convertList02(List<ShopServiceDO> list);

}
