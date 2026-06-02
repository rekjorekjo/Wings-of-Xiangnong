package com.ordering.module.coupon.convert.coupon;

import java.util.*;

import com.ordering.framework.common.pojo.PageResult;

import com.ordering.module.coupon.controller.app.coupon.vo.AppCouponVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ordering.module.coupon.controller.admin.coupon.vo.*;
import com.ordering.module.coupon.dal.dataobject.coupon.CouponDO;

/**
 * 优惠券 Convert
 *
 * @author project team
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
