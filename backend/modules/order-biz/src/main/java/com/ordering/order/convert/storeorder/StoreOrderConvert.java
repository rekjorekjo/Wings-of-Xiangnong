package com.ordering.order.convert.storeorder;

import java.util.*;

import com.ordering.framework.common.pojo.PageResult;

import com.ordering.order.controller.app.order.vo.AppStoreOrderQueryVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ordering.order.controller.admin.storeorder.vo.*;
import com.ordering.order.dal.dataobject.storeorder.StoreOrderDO;

/**
 * 订单 Convert
 *
 * @author project team
 */
@Mapper
public interface StoreOrderConvert {

    StoreOrderConvert INSTANCE = Mappers.getMapper(StoreOrderConvert.class);

    StoreOrderDO convert(StoreOrderCreateReqVO bean);

    StoreOrderDO convert(StoreOrderUpdateReqVO bean);

    StoreOrderRespVO convert(StoreOrderDO bean);

    AppStoreOrderQueryVo convert1(StoreOrderDO bean);

    List<StoreOrderRespVO> convertList(List<StoreOrderDO> list);

    List<AppStoreOrderQueryVo> convertList01(List<StoreOrderDO> list);

    PageResult<StoreOrderRespVO> convertPage(PageResult<StoreOrderDO> page);

    List<StoreOrderExcelVO> convertList02(List<StoreOrderDO> list);

}
