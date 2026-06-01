package com.wings.module.coupon.convert.coupon;

import java.util.*;

import com.wings.framework.common.pojo.PageResult;

import com.wings.module.coupon.controller.app.coupon.vo.AppCouponVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.wings.module.coupon.controller.admin.coupon.vo.*;
import com.wings.module.coupon.dal.dataobject.coupon.CouponDO;

/**
 * 优惠券 Convert
 *
 * @author wings
 */
@Mapper
public interface CouponConvert {

    CouponConvert INSTANCE = Mappers.getMapper(CouponConvert.class);

    CouponDO convert(CouponCreateReqVO bean);

    CouponDO convert(CouponUpdateReqVO bean);

    CouponRespVO convert(CouponDO bean);

    AppCouponVO convert01(CouponDO bean);

    List<CouponRespVO> convertList(List<CouponDO> list);

    List<AppCouponVO> convertList03(List<CouponDO> list);

    PageResult<CouponRespVO> convertPage(PageResult<CouponDO> page);

    List<CouponExcelVO> convertList02(List<CouponDO> list);

}
