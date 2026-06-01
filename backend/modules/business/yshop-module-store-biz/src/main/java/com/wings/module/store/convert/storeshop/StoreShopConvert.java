package com.wings.module.store.convert.storeshop;

import java.util.*;

import com.wings.framework.common.pojo.PageResult;

import com.wings.module.store.controller.app.storeshop.vo.AppStoreShopVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.wings.module.store.controller.admin.storeshop.vo.*;
import com.wings.module.store.dal.dataobject.storeshop.StoreShopDO;

/**
 * 门店管理 Convert
 *
 * @author wings
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
