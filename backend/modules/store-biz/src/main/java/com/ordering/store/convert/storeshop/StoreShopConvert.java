package com.ordering.store.convert.storeshop;

import java.util.*;

import com.ordering.framework.common.pojo.PageResult;

import com.ordering.store.controller.app.storeshop.vo.AppStoreShopVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ordering.store.controller.admin.storeshop.vo.*;
import com.ordering.store.dal.dataobject.storeshop.StoreShopDO;

/**
 * 门店管理 Convert
 *
 * @author project team
 */
@Mapper
public interface StoreShopConvert {

    StoreShopConvert INSTANCE = Mappers.getMapper(StoreShopConvert.class);

    StoreShopDO convert(StoreShopCreateReqVO bean);

    StoreShopDO convert(StoreShopUpdateReqVO bean);

    StoreShopRespVO convert(StoreShopDO bean);

    AppStoreShopVO convert02(StoreShopDO bean);

    List<StoreShopRespVO> convertList(List<StoreShopDO> list);

    PageResult<StoreShopRespVO> convertPage(PageResult<StoreShopDO> page);

    List<StoreShopExcelVO> convertList02(List<StoreShopDO> list);

}
