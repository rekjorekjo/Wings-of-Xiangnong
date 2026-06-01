package com.wings.module.coupon.convert.couponuser;

import java.util.*;

import com.wings.framework.common.pojo.PageResult;

import com.wings.module.coupon.controller.app.coupon.vo.AppMyCouponVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.wings.module.coupon.controller.admin.couponuser.vo.*;
import com.wings.module.coupon.dal.dataobject.couponuser.CouponUserDO;

/**
 * 用户领的优惠券 Convert
 *
 * @author wings
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
