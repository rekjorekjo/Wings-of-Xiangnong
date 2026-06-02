package com.ordering.module.product.dal.mysql.storeproductrule;

import java.util.*;

import com.ordering.framework.common.pojo.PageResult;
import com.ordering.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ordering.framework.mybatis.core.mapper.BaseMapperX;
import com.ordering.module.product.dal.dataobject.storeproductrule.StoreProductRuleDO;
import org.apache.ibatis.annotations.Mapper;
import com.ordering.module.product.controller.admin.storeproductrule.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 商品规则值(规格) Mapper
 *
 * @author project team
 */
@Mapper
public interface StoreProductRuleMapper extends BaseMapperX<StoreProductRuleDO> {

    default PageResult<StoreProductRuleDO> selectPage(StoreProductRulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StoreProductRuleDO>()
                .likeIfPresent(StoreProductRuleDO::getRuleName, reqVO.getRuleName())
                .orderByDesc(StoreProductRuleDO::getId));
    }

    default List<StoreProductRuleDO> selectList(StoreProductRuleExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<StoreProductRuleDO>()
                .likeIfPresent(StoreProductRuleDO::getRuleName, reqVO.getRuleName())
                .orderByDesc(StoreProductRuleDO::getId));
    }



}
