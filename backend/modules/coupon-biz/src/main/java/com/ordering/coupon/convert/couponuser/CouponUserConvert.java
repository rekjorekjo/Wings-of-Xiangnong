package com.ordering.coupon.convert.couponuser;

import java.util.*;

import com.ordering.framework.common.pojo.PageResult;

import com.ordering.coupon.controller.app.coupon.vo.AppMyCouponVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ordering.coupon.controller.admin.couponuser.vo.*;
import com.ordering.coupon.dal.dataobject.couponuser.CouponUserDO;

/**
 * 用户领的优惠券 Convert
 *
 * @author project team
 */
@Mapper
public interface CouponUserConvert {

    CouponUserConvert INSTANCE = Mappers.getMapper(CouponUserConvert.class);

    CouponUserDO convert(CouponUserCreateReqVO bean);

    CouponUserDO convert(CouponUserUpdateReqVO bean);

    CouponUserRespVO convert(CouponUserDO bean);

    List<AppMyCouponVO> convertList03(List<CouponUserDO> list);

    List<CouponUserRespVO> convertList(List<CouponUserDO> list);

    PageResult<CouponUserRespVO> convertPage(PageResult<CouponUserDO> page);

    List<CouponUserExcelVO> convertList02(List<CouponUserDO> list);

}
