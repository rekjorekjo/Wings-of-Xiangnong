package com.wings.module.product.convert.storeproductrule;

import java.util.*;

import com.wings.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.wings.module.product.controller.admin.storeproductrule.vo.*;
import com.wings.module.product.dal.dataobject.storeproductrule.StoreProductRuleDO;

/**
 * 商品规则值(规格) Convert
 *
 * @author wings
 */
@Mapper
public interface StoreProductRuleConvert {

    StoreProductRuleConvert INSTANCE = Mappers.getMapper(StoreProductRuleConvert.class);

    StoreProductRuleDO convert(StoreProductRuleCreateReqVO bean);

    StoreProductRuleDO convert(StoreProductRuleUpdateReqVO bean);

    StoreProductRuleRespVO convert(StoreProductRuleDO bean);

    List<StoreProductRuleRespVO> convertList(List<StoreProductRuleDO> list);

    PageResult<StoreProductRuleRespVO> convertPage(PageResult<StoreProductRuleDO> page);

    List<StoreProductRuleExcelVO> convertList02(List<StoreProductRuleDO> list);

}
