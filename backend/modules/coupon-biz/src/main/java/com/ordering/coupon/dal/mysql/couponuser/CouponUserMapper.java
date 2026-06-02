package com.ordering.coupon.dal.mysql.couponuser;

import java.util.*;

import com.ordering.framework.common.pojo.PageResult;
import com.ordering.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ordering.framework.mybatis.core.mapper.BaseMapperX;
import com.ordering.coupon.dal.dataobject.couponuser.CouponUserDO;
import org.apache.ibatis.annotations.Mapper;
import com.ordering.coupon.controller.admin.couponuser.vo.*;

/**
 * 用户领的优惠券 Mapper
 *
 * @author project team
 */
@Mapper
public interface CouponUserMapper extends BaseMapperX<CouponUserDO> {

    default PageResult<CouponUserDO> selectPage(CouponUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CouponUserDO>()
                .eqIfPresent(CouponUserDO::getShopId, reqVO.getShopId())
                .likeIfPresent(CouponUserDO::getShopName, reqVO.getShopName())
                .eqIfPresent(CouponUserDO::getTitle, reqVO.getTitle())
                .eqIfPresent(CouponUserDO::getLeast, reqVO.getLeast())
                .eqIfPresent(CouponUserDO::getValue, reqVO.getValue())
                .eqIfPresent(CouponUserDO::getType, reqVO.getType())
                .eqIfPresent(CouponUserDO::getScore, reqVO.getScore())
                .eqIfPresent(CouponUserDO::getInstructions, reqVO.getInstructions())
                .eqIfPresent(CouponUserDO::getImage, reqVO.getImage())
                .eqIfPresent(CouponUserDO::getUserId, reqVO.getUserId())
                .eqIfPresent(CouponUserDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CouponUserDO::getCouponId, reqVO.getCouponId())
                .eqIfPresent(CouponUserDO::getExchangeCode, reqVO.getExchangeCode())
                .orderByDesc(CouponUserDO::getId));
    }

    default List<CouponUserDO> selectList(CouponUserExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CouponUserDO>()
                .eqIfPresent(CouponUserDO::getShopId, reqVO.getShopId())
                .likeIfPresent(CouponUserDO::getShopName, reqVO.getShopName())
                .eqIfPresent(CouponUserDO::getTitle, reqVO.getTitle())
                .eqIfPresent(CouponUserDO::getLeast, reqVO.getLeast())
                .eqIfPresent(CouponUserDO::getValue, reqVO.getValue())
                .eqIfPresent(CouponUserDO::getType, reqVO.getType())
                .eqIfPresent(CouponUserDO::getScore, reqVO.getScore())
                .eqIfPresent(CouponUserDO::getInstructions, reqVO.getInstructions())
                .eqIfPresent(CouponUserDO::getImage, reqVO.getImage())
                .eqIfPresent(CouponUserDO::getUserId, reqVO.getUserId())
                .eqIfPresent(CouponUserDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CouponUserDO::getCouponId, reqVO.getCouponId())
                .eqIfPresent(CouponUserDO::getExchangeCode, reqVO.getExchangeCode())
                .orderByDesc(CouponUserDO::getId));
    }

}
