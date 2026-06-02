package com.ordering.shop.dal.mysql.shopservice;

import java.util.*;

import com.ordering.framework.common.pojo.PageResult;
import com.ordering.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ordering.framework.mybatis.core.mapper.BaseMapperX;
import com.ordering.shop.dal.dataobject.shopservice.ShopServiceDO;
import org.apache.ibatis.annotations.Mapper;
import com.ordering.shop.controller.admin.shopservice.vo.*;

/**
 * 我的服务 Mapper
 *
 * @author project team
 */
@Mapper
public interface ShopServiceMapper extends BaseMapperX<ShopServiceDO> {

    default PageResult<ShopServiceDO> selectPage(ShopServicePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ShopServiceDO>()
                .likeIfPresent(ShopServiceDO::getName, reqVO.getName())
                .eqIfPresent(ShopServiceDO::getImage, reqVO.getImage())
                .eqIfPresent(ShopServiceDO::getType, reqVO.getType())
                .eqIfPresent(ShopServiceDO::getContent, reqVO.getContent())
                .eqIfPresent(ShopServiceDO::getPid, reqVO.getPid())
                .eqIfPresent(ShopServiceDO::getAppId, reqVO.getAppId())
                .eqIfPresent(ShopServiceDO::getPages, reqVO.getPages())
                .eqIfPresent(ShopServiceDO::getPhone, reqVO.getPhone())
                .eqIfPresent(ShopServiceDO::getWeigh, reqVO.getWeigh())
                .eqIfPresent(ShopServiceDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ShopServiceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ShopServiceDO::getId));
    }

    default List<ShopServiceDO> selectList(ShopServiceExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ShopServiceDO>()
                .likeIfPresent(ShopServiceDO::getName, reqVO.getName())
                .eqIfPresent(ShopServiceDO::getImage, reqVO.getImage())
                .eqIfPresent(ShopServiceDO::getType, reqVO.getType())
                .eqIfPresent(ShopServiceDO::getContent, reqVO.getContent())
                .eqIfPresent(ShopServiceDO::getPid, reqVO.getPid())
                .eqIfPresent(ShopServiceDO::getAppId, reqVO.getAppId())
                .eqIfPresent(ShopServiceDO::getPages, reqVO.getPages())
                .eqIfPresent(ShopServiceDO::getPhone, reqVO.getPhone())
                .eqIfPresent(ShopServiceDO::getWeigh, reqVO.getWeigh())
                .eqIfPresent(ShopServiceDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ShopServiceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ShopServiceDO::getId));
    }

}
